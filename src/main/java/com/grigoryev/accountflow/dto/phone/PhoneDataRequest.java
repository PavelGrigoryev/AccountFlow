package com.grigoryev.accountflow.dto.phone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record PhoneDataRequest(
        @NotBlank
        @Pattern(regexp = "[78]\\d{10}", message = "Phone number must be 11 digits starting with 7 or 8 (example: 79207865432)")
        String phone) {
}
