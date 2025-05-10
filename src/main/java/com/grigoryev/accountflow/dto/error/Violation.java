package com.grigoryev.accountflow.dto.error;

public record Violation(String fieldName, String message) {
}
