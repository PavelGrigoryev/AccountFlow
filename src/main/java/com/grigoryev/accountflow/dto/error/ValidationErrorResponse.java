package com.grigoryev.accountflow.dto.error;

import java.util.List;

public record ValidationErrorResponse(String errorMessage, List<Violation> violations) {
}
