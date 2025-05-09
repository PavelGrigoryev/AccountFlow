package com.grigoryev.accountflow.service;

import com.grigoryev.accountflow.dto.DeleteResponse;
import com.grigoryev.accountflow.dto.email.EmailDataRequest;
import com.grigoryev.accountflow.dto.email.EmailDataResponse;
import com.grigoryev.accountflow.exception.EmailDataNotFoundException;
import com.grigoryev.accountflow.exception.UserNotFoundException;
import com.grigoryev.accountflow.mapper.EmailDataMapper;
import com.grigoryev.accountflow.model.EmailData;
import com.grigoryev.accountflow.model.User;
import com.grigoryev.accountflow.repository.EmailDataRepository;
import com.grigoryev.accountflow.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class EmailDataService {

    private final EmailDataRepository emailDataRepository;
    private final UserRepository userRepository;
    private final EmailDataMapper emailDataMapper;

    @Transactional
    public EmailDataResponse save(Long userId, EmailDataRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = %d is not found".formatted(userId)));
        EmailData emailData = emailDataMapper.toEmailData(request, user);
        EmailData saved = emailDataRepository.save(emailData);
        return emailDataMapper.toEmailDataResponse(saved);
    }

    public EmailDataResponse update(Long id, EmailDataRequest request) {
        return emailDataRepository.findById(id)
                .map(data -> data.setEmail(request.email()))
                .map(emailDataRepository::save)
                .map(emailDataMapper::toEmailDataResponse)
                .orElseThrow(throwEmailDataNotFoundException(id));
    }

    public DeleteResponse delete(Long id) {
        return emailDataRepository.findById(id)
                .map(data -> {
                    emailDataRepository.deleteById(id);
                    return new DeleteResponse("EmailData with id = %d is deleted".formatted(id));
                })
                .orElseThrow(throwEmailDataNotFoundException(id));
    }

    private Supplier<EmailDataNotFoundException> throwEmailDataNotFoundException(Long id) {
        return () -> new EmailDataNotFoundException("Email data with id = %d is not found".formatted(id));
    }

}
