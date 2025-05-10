package com.grigoryev.accountflow.dto.account;

import com.grigoryev.accountflow.dto.user.UserResponse;

import java.io.Serializable;
import java.math.BigDecimal;

public record AccountResponse(Long id, BigDecimal balance, UserResponse user) implements Serializable {
}
