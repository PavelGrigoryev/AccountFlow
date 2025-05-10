package com.grigoryev.accountflow.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginEmailRequest(@Email
                                @NotBlank
                                String email,

                                @NotBlank
                                String password) {
}
