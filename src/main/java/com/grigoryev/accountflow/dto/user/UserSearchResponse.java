package com.grigoryev.accountflow.dto.user;

import com.grigoryev.accountflow.dto.email.EmailDataResponse;
import com.grigoryev.accountflow.dto.phone.PhoneDataResponse;

import java.time.LocalDate;
import java.util.List;

public record UserSearchResponse(Long id,
                                 String name,
                                 LocalDate dateOfBirth,
                                 List<EmailDataResponse> emails,
                                 List<PhoneDataResponse> phones) {
}
