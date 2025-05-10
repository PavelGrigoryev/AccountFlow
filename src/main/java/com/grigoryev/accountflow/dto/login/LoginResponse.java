package com.grigoryev.accountflow.dto.login;

import java.io.Serializable;

public record LoginResponse(String token) implements Serializable {
}
