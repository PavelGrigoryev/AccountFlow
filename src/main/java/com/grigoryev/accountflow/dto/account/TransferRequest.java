package com.grigoryev.accountflow.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(example = """
        {
          "toUserId" : 2,
          "amount" : 50
        }
        """)
public record TransferRequest(@NotNull
                              Long toUserId,

                              @NotNull
                              @Positive
                              BigDecimal amount) {
}