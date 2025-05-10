package com.grigoryev.accountflow.dto.user;

import com.grigoryev.accountflow.validation.ValidDateOfBirth;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserSearchRequest(

        @Size(max = 500)
        String name,

        @ValidDateOfBirth
        String dateOfBirth,

        @NotBlank
        @Pattern(regexp = "[78]\\d{10}", message = "Phone number must be 11 digits starting with 7 or 8 (example: 79207865432)")
        String phone,

        @Email
        @NotBlank
        String email,

        Integer page,
        Integer size) {
}
