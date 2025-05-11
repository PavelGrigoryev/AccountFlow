package com.grigoryev.accountflow.controller;

import com.grigoryev.accountflow.config.BaseIntegrationTest;
import com.grigoryev.accountflow.model.Account;
import com.grigoryev.accountflow.repository.AccountRepository;
import com.grigoryev.accountflow.service.JwtService;
import com.grigoryev.accountflow.util.JsonUtil;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class AccountControllerTest extends BaseIntegrationTest {

    private final MockMvc mockMvc;
    private final AccountRepository accountRepository;

    @MockitoBean
    private final JwtService jwtService;

    @Test
    @SneakyThrows
    void testFindWithUserByIdShouldReturnExpectedResult() {
        String json = JsonUtil.getJson("__files/account-response-ok.json");

        mockMvc.perform(get("/accounts/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    @SneakyThrows
    void testFindWithUserByIdShouldReturnAccountNotFoundException() {
        String json = JsonUtil.getJson("__files/account-response-404.json");

        mockMvc.perform(get("/accounts/111"))
                .andExpect(status().isNotFound())
                .andExpect(content().json(json));
    }

    @Test
    @SneakyThrows
    void testTransferMoneyShouldReturnExpectedResultWithSuccessfulTransfer() {
        String token = "jwt";
        String body = JsonUtil.getJson("__files/transfer-request-ok.json");
        String json = JsonUtil.getJson("__files/transfer-response-ok.json");

        Mockito.when(jwtService.getUserIdFromToken(token)).thenReturn(1L);

        accountRepository.findById(1L)
                .map(Account::getBalance)
                .ifPresent(balance -> assertThat(balance).isEqualTo(BigDecimal.valueOf(5000.00)
                        .setScale(2, RoundingMode.HALF_UP)));
        accountRepository.findById(2L)
                .map(Account::getBalance)
                .ifPresent(balance -> assertThat(balance).isEqualTo(BigDecimal.valueOf(3000.00)
                        .setScale(2, RoundingMode.HALF_UP)));

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("USER_ID", token))
                .andExpect(status().isOk())
                .andExpect(content().json(json));

        accountRepository.findById(1L)
                .map(Account::getBalance)
                .ifPresent(balance -> assertThat(balance).isEqualTo(BigDecimal.valueOf(4950.00)
                        .setScale(2, RoundingMode.HALF_UP)));
        accountRepository.findById(2L)
                .map(Account::getBalance)
                .ifPresent(balance -> assertThat(balance).isEqualTo(BigDecimal.valueOf(3050.00)
                        .setScale(2, RoundingMode.HALF_UP)));
    }

    @Test
    @SneakyThrows
    void testTransferMoneyShouldReturnValidationException() {
        String token = "jwt";
        String body = JsonUtil.getJson("__files/transfer-request-409.json");
        String json = JsonUtil.getJson("__files/transfer-response-409.json");

        Mockito.when(jwtService.getUserIdFromToken(token)).thenReturn(1L);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("USER_ID", token))
                .andExpect(status().isConflict())
                .andExpect(content().json(json));
    }

    @Test
    @SneakyThrows
    void testTransferMoneyShouldReturnJwtException() {
        String token = "jwt";
        String body = JsonUtil.getJson("__files/transfer-request-ok.json");
        String json = JsonUtil.getJson("__files/transfer-response-403.json");

        Mockito.when(jwtService.getUserIdFromToken(token)).thenThrow(new JwtException("Invalid/Expired JWT token"));

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("USER_ID", token))
                .andExpect(status().isForbidden())
                .andExpect(content().json(json));
    }

    @SneakyThrows
    @ParameterizedTest
    @ValueSource(strings = {" ", "Something"})
    void testTransferMoneyShouldReturnUserIdHeaderNotValidException(String header) {
        String token = "jwt";
        String body = JsonUtil.getJson("__files/transfer-request-ok.json");
        String json = JsonUtil.getJson("__files/transfer-response-bad-header-403.json");

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header(header, token))
                .andExpect(status().isForbidden())
                .andExpect(content().json(json));
    }

    @Test
    @SneakyThrows
    void testTransferMoneyShouldReturnTransferFundsException() {
        String token = "jwt";
        String body = JsonUtil.getJson("__files/transfer-request-ok.json");
        String json = JsonUtil.getJson("__files/transfer-response-406.json");

        Mockito.when(jwtService.getUserIdFromToken(token)).thenReturn(2L);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("USER_ID", token))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().json(json));
    }

    @Test
    @SneakyThrows
    void testTransferMoneyShouldReturnUserNotFoundExceptionForSender() {
        String token = "jwt";
        String body = JsonUtil.getJson("__files/transfer-request-ok.json");
        String json = JsonUtil.getJson("__files/transfer-response-404.json");

        Mockito.when(jwtService.getUserIdFromToken(token)).thenReturn(111L);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("USER_ID", token))
                .andExpect(status().isNotFound())
                .andExpect(content().json(json));
    }

    @Test
    @SneakyThrows
    void testTransferMoneyShouldReturnUserNotFoundExceptionForRecipient() {
        String token = "jwt";
        String body = JsonUtil.getJson("__files/transfer-request-with-not-exist-user-406.json");
        String json = JsonUtil.getJson("__files/transfer-response-for-recipient-403.json");

        Mockito.when(jwtService.getUserIdFromToken(token)).thenReturn(1L);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("USER_ID", token))
                .andExpect(status().isNotFound())
                .andExpect(content().json(json));
    }

    @Test
    @SneakyThrows
    void testTransferMoneyShouldReturnTransferFundsExceptionForSender() {
        String token = "jwt";
        String body = JsonUtil.getJson("__files/transfer-request-with-many-funds.json");
        String json = JsonUtil.getJson("__files/transfer-response-bad-funds.json");

        Mockito.when(jwtService.getUserIdFromToken(token)).thenReturn(1L);

        mockMvc.perform(post("/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body)
                        .header("USER_ID", token))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().json(json));
    }

}
