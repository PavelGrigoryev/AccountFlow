package com.grigoryev.accountflow.service;

import com.grigoryev.accountflow.dto.DeleteResponse;
import com.grigoryev.accountflow.dto.phone.PhoneDataRequest;
import com.grigoryev.accountflow.dto.phone.PhoneDataResponse;
import com.grigoryev.accountflow.exception.PhoneDataNotFoundException;
import com.grigoryev.accountflow.exception.UserNotFoundException;
import com.grigoryev.accountflow.mapper.PhoneDataMapper;
import com.grigoryev.accountflow.model.PhoneData;
import com.grigoryev.accountflow.model.User;
import com.grigoryev.accountflow.repository.PhoneDataRepository;
import com.grigoryev.accountflow.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
@Transactional
@RequiredArgsConstructor
public class PhoneDataService {

    private final PhoneDataRepository phoneDataRepository;
    private final UserRepository userRepository;
    private final PhoneDataMapper phoneDataMapper;

    public PhoneDataResponse save(Long userId, PhoneDataRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = %d is not found".formatted(userId)));
        PhoneData phoneData = phoneDataMapper.toPhoneData(request, user);
        PhoneData saved = phoneDataRepository.save(phoneData);
        return phoneDataMapper.toPhoneDataResponse(saved);
    }

    public PhoneDataResponse update(Long id, PhoneDataRequest request) {
        return phoneDataRepository.findById(id)
                .map(data -> data.setPhone(request.phone()))
                .map(phoneDataRepository::save)
                .map(phoneDataMapper::toPhoneDataResponse)
                .orElseThrow(throwPhoneDataNotFoundException(id));
    }

    public DeleteResponse delete(Long id) {
        return phoneDataRepository.findById(id)
                .map(data -> {
                    phoneDataRepository.deleteById(id);
                    return new DeleteResponse("PhoneData with id = %d is deleted".formatted(id));
                })
                .orElseThrow(throwPhoneDataNotFoundException(id));
    }

    private Supplier<PhoneDataNotFoundException> throwPhoneDataNotFoundException(Long id) {
        return () -> new PhoneDataNotFoundException("PhoneData with id = %d is not found".formatted(id));
    }

}
