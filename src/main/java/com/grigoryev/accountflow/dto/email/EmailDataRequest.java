package com.grigoryev.accountflow.dto.email;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(example = """
        {
          "email": "some.personal@example.com"
        }
        """)
public record EmailDataRequest(@Email @NotBlank String email) {
}
