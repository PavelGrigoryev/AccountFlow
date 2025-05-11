package com.grigoryev.accountflow.controller.openapi;

import com.grigoryev.accountflow.dto.user.UserSearchRequest;
import com.grigoryev.accountflow.dto.user.UserSearchResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserOpenApi {

    @Operation(summary = "Поиск пользователей с фильтрацией по полям")
    ResponseEntity<List<UserSearchResponse>> searchUsers(UserSearchRequest request);

}
