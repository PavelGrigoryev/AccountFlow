package com.grigoryev.accountflow.dto.email;

import java.io.Serializable;

public record EmailDataResponse(Long id, String email) implements Serializable {
}
