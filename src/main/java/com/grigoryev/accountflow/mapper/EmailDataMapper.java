package com.grigoryev.accountflow.mapper;

import com.grigoryev.accountflow.dto.email.EmailDataRequest;
import com.grigoryev.accountflow.dto.email.EmailDataResponse;
import com.grigoryev.accountflow.model.EmailData;
import com.grigoryev.accountflow.model.User;
import org.springframework.stereotype.Component;

@Component
public class EmailDataMapper {

    public EmailData toEmailData(EmailDataRequest request, User user) {
        return new EmailData().setEmail(request.email())
                .setUser(user);
    }

    public EmailDataResponse toEmailDataResponse(EmailData emailData) {
        return new EmailDataResponse(emailData.getId(), emailData.getEmail());
    }

}
