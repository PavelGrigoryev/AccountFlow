package com.grigoryev.accountflow.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(example = """
        {
          "phone": "79109876543",
          "password": "ivan12345678"
        }
        """)
public record LoginPhoneRequest(@NotBlank
                                @Pattern(regexp = "[78]\\d{10}", message = "Phone number must be 11 digits starting with 7 or 8 (example: 79207865432)")
                                String phone,

                                @NotBlank
                                String password) {
}
