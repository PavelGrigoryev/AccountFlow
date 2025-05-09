package com.grigoryev.accountflow.controller;

import com.grigoryev.accountflow.dto.user.UserSearchRequest;
import com.grigoryev.accountflow.dto.user.UserSearchResponse;
import com.grigoryev.accountflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<UserSearchResponse>> searchUsers(UserSearchRequest request) {
        return ResponseEntity.ok(userService.searchUsers(request));
    }

}
