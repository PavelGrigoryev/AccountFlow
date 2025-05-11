package com.grigoryev.accountflow.controller;

import com.grigoryev.accountflow.aspect.Loggable;
import com.grigoryev.accountflow.controller.openapi.UserOpenApi;
import com.grigoryev.accountflow.dto.user.UserSearchRequest;
import com.grigoryev.accountflow.dto.user.UserSearchResponse;
import com.grigoryev.accountflow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Loggable
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController implements UserOpenApi {

    private final UserService userService;

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<UserSearchResponse>> searchUsers(@Valid UserSearchRequest request) {
        return ResponseEntity.ok(userService.searchUsers(request));
    }

}
