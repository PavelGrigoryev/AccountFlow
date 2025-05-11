package com.grigoryev.accountflow.dto.login;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(example = """
        {
          "email": "ivan.ivanov@example.com",
          "password": "ivan12345678"
        }
        """)
public record LoginEmailRequest(@Email
                                @NotBlank
                                String email,

                                @NotBlank
                                String password) {
}
