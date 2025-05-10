package com.grigoryev.accountflow.mapper;

import com.grigoryev.accountflow.dto.email.EmailDataResponse;
import com.grigoryev.accountflow.dto.phone.PhoneDataResponse;
import com.grigoryev.accountflow.dto.user.UserResponse;
import com.grigoryev.accountflow.dto.user.UserSearchResponse;
import com.grigoryev.accountflow.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final EmailDataMapper emailDataMapper;
    private final PhoneDataMapper phoneDataMapper;

    public UserSearchResponse toUserSearchResponse(User user) {
        List<EmailDataResponse> emails = user.getEmails()
                .stream()
                .map(emailDataMapper::toEmailDataResponse)
                .toList();
        List<PhoneDataResponse> phones = user.getPhones()
                .stream()
                .map(phoneDataMapper::toPhoneDataResponse)
                .toList();
        return new UserSearchResponse(user.getId(), user.getName(), user.getDateOfBirth(), emails, phones);
    }

    public UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getDateOfBirth());
    }

}
