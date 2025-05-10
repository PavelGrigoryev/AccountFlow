package com.grigoryev.accountflow.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(Long id, String name, String dateOfBirth) implements Serializable {
}
