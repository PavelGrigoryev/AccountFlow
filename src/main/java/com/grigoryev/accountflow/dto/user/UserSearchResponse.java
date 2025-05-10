package com.grigoryev.accountflow.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.grigoryev.accountflow.dto.email.EmailDataResponse;
import com.grigoryev.accountflow.dto.phone.PhoneDataResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserSearchResponse(Long id,
                                 String name,
                                 String dateOfBirth,
                                 List<EmailDataResponse> emails,
                                 List<PhoneDataResponse> phones) {
}
