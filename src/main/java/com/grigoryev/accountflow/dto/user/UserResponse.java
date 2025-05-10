package com.grigoryev.accountflow.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(Long id, String name, LocalDate dateOfBirth) implements Serializable {
}
