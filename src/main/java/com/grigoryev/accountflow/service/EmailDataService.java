package com.grigoryev.accountflow.service;

import com.grigoryev.accountflow.dto.DeleteResponse;
import com.grigoryev.accountflow.dto.email.EmailDataRequest;
import com.grigoryev.accountflow.dto.email.EmailDataResponse;
import com.grigoryev.accountflow.exception.EmailDataNotFoundException;
import com.grigoryev.accountflow.interceptor.UserHolder;
import com.grigoryev.accountflow.mapper.EmailDataMapper;
import com.grigoryev.accountflow.model.EmailData;
import com.grigoryev.accountflow.repository.EmailDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailDataService {

    private final EmailDataRepository emailDataRepository;
    private final EmailDataMapper emailDataMapper;
    private final UserHolder userHolder;

    @Transactional(readOnly = true)
    @Cacheable(value = "emails", key = "#id")
    public EmailDataResponse findById(Long id) {
        return emailDataRepository.findById(id)
                .map(emailDataMapper::toEmailDataResponse)
                .orElseThrow(throwEmailDataNotFoundException(id));
    }

    @CachePut(value = "emails", key = "#result.id()")
    public EmailDataResponse save(EmailDataRequest request) {
        EmailData emailData = emailDataMapper.toEmailData(request, userHolder.getUser());
        EmailData saved = emailDataRepository.save(emailData);
        return emailDataMapper.toEmailDataResponse(saved);
    }

    @CachePut(value = "emails", key = "#result.id()")
    public EmailDataResponse update(Long id, EmailDataRequest request) {
        return emailDataRepository.findWithUserById(id)
                .filter(data -> data.getUser().getId().equals(userHolder.getUser().getId()))
                .map(data -> data.setEmail(request.email()))
                .map(emailDataRepository::save)
                .map(emailDataMapper::toEmailDataResponse)
                .orElseThrow(throwEmailDataNotFoundException(id));
    }

    @CacheEvict(value = "emails", key = "#id")
    public DeleteResponse delete(Long id) {
        return emailDataRepository.findWithUserById(id)
                .filter(data -> data.getUser().getId().equals(userHolder.getUser().getId()))
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
