package com.grigoryev.accountflow.controller.openapi;

import com.grigoryev.accountflow.dto.login.LoginEmailRequest;
import com.grigoryev.accountflow.dto.login.LoginPhoneRequest;
import com.grigoryev.accountflow.dto.login.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;

public interface LoginOpenApi {

    @Operation(summary = "Получение jwt с привязкой USER_ID по телефону и паролю")
    ResponseEntity<LoginResponse> loginByPhone(LoginPhoneRequest request);

    @Operation(summary = "Получение jwt с привязкой USER_ID по почте и паролю")
    ResponseEntity<LoginResponse> loginByEmail(LoginEmailRequest request);

}
