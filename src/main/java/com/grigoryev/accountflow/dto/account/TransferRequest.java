package com.grigoryev.accountflow.dto.account;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransferRequest(@NotNull
                              Long toUserId,

                              @NotNull
                              @Positive
                              BigDecimal amount) {
}