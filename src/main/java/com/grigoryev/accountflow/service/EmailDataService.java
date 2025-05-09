package com.grigoryev.accountflow.service;

import com.grigoryev.accountflow.dto.email.EmailDataRequest;
import com.grigoryev.accountflow.dto.email.EmailDataResponse;
import com.grigoryev.accountflow.exception.UserNotFoundException;
import com.grigoryev.accountflow.mapper.EmailDataMapper;
import com.grigoryev.accountflow.model.EmailData;
import com.grigoryev.accountflow.model.User;
import com.grigoryev.accountflow.repository.EmailDataRepository;
import com.grigoryev.accountflow.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailDataService {

    private final EmailDataRepository emailDataRepository;
    private final UserRepository userRepository;
    private final EmailDataMapper emailDataMapper;

    @Transactional
    public EmailDataResponse save(EmailDataRequest request) {
        Long userId = request.userId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = %d is not found".formatted(userId)));
        EmailData emailData = emailDataMapper.toEmailData(request, user);
        EmailData saved = emailDataRepository.save(emailData);
        return emailDataMapper.toEmailDataResponse(saved);
    }

}
