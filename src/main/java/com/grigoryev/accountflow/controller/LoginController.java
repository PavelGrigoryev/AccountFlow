package com.grigoryev.accountflow.controller;

import com.grigoryev.accountflow.dto.login.LoginEmailRequest;
import com.grigoryev.accountflow.dto.login.LoginPhoneRequest;
import com.grigoryev.accountflow.dto.login.LoginResponse;
import com.grigoryev.accountflow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final UserService userService;

    @PostMapping("/phone")
    public ResponseEntity<LoginResponse> loginByPhone(@RequestBody @Valid LoginPhoneRequest request) {
        return ResponseEntity.ok(userService.loginByPhone(request));
    }

    @PostMapping("/email")
    public ResponseEntity<LoginResponse> loginByEmail(@RequestBody @Valid LoginEmailRequest request) {
        return ResponseEntity.ok(userService.loginByEmail(request));
    }

}
