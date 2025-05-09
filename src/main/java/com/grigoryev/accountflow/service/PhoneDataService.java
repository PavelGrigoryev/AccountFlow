package com.grigoryev.accountflow.service;

import com.grigoryev.accountflow.dto.phone.PhoneDataRequest;
import com.grigoryev.accountflow.dto.phone.PhoneDataResponse;
import com.grigoryev.accountflow.exception.UserNotFoundException;
import com.grigoryev.accountflow.mapper.PhoneDataMapper;
import com.grigoryev.accountflow.model.PhoneData;
import com.grigoryev.accountflow.model.User;
import com.grigoryev.accountflow.repository.PhoneDataRepository;
import com.grigoryev.accountflow.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PhoneDataService {

    private final PhoneDataRepository phoneDataRepository;
    private final UserRepository userRepository;
    private final PhoneDataMapper phoneDataMapper;

    @Transactional
    public PhoneDataResponse save(PhoneDataRequest request) {
        Long userId = request.userId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id = %d is not found".formatted(userId))); //TODO вынести
        PhoneData phoneData = phoneDataMapper.toPhoneData(request, user);
        PhoneData saved = phoneDataRepository.save(phoneData);
        return phoneDataMapper.toPhoneDataResponse(saved);
    }

}
