package com.grigoryev.accountflow.controller;

import com.grigoryev.accountflow.aspect.Loggable;
import com.grigoryev.accountflow.dto.account.AccountResponse;
import com.grigoryev.accountflow.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Loggable
@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> findWithUserById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.findWithUserById(id));
    }

}
