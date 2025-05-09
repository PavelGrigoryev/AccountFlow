package com.grigoryev.accountflow.dto.user;

import java.time.LocalDate;

public record UserSearchRequest(String name,
                                LocalDate dateOfBirth,
                                String phone,
                                String email,
                                Integer page,
                                Integer size) {
}
