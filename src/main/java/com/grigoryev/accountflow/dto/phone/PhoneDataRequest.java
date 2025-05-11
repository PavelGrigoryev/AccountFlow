package com.grigoryev.accountflow.dto.phone;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(example = """
        {
          "phone": "79297865455"
        }
        """)
public record PhoneDataRequest(
        @NotBlank
        @Pattern(regexp = "[78]\\d{10}", message = "Phone number must be 11 digits starting with 7 or 8 (example: 79207865432)")
        String phone) {
}
