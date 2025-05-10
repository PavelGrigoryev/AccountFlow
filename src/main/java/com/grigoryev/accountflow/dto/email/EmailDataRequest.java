package com.grigoryev.accountflow.dto.email;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailDataRequest(@Email @NotBlank String email) {
}
