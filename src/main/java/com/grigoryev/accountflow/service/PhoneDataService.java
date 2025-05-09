package com.grigoryev.accountflow.service;

import com.grigoryev.accountflow.dto.DeleteResponse;
import com.grigoryev.accountflow.dto.phone.PhoneDataRequest;
import com.grigoryev.accountflow.dto.phone.PhoneDataResponse;
import com.grigoryev.accountflow.exception.PhoneDataNotFoundException;
import com.grigoryev.accountflow.interceptor.UserHolder;
import com.grigoryev.accountflow.mapper.PhoneDataMapper;
import com.grigoryev.accountflow.model.PhoneData;
import com.grigoryev.accountflow.repository.PhoneDataRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PhoneDataService {

    private final PhoneDataRepository phoneDataRepository;
    private final PhoneDataMapper phoneDataMapper;
    private final UserHolder userHolder;

    public PhoneDataResponse save(PhoneDataRequest request) {
        PhoneData phoneData = phoneDataMapper.toPhoneData(request, userHolder.getUser());
        PhoneData saved = phoneDataRepository.save(phoneData);
        return phoneDataMapper.toPhoneDataResponse(saved);
    }

    public PhoneDataResponse update(Long id, PhoneDataRequest request) {
        return phoneDataRepository.findWithUserById(id)
                .filter(data -> data.getUser().getId().equals(userHolder.getUser().getId()))
                .map(data -> data.setPhone(request.phone()))
                .map(phoneDataRepository::save)
                .map(phoneDataMapper::toPhoneDataResponse)
                .orElseThrow(throwPhoneDataNotFoundException(id));
    }

    public DeleteResponse delete(Long id) {
        return phoneDataRepository.findWithUserById(id)
                .filter(data -> data.getUser().getId().equals(userHolder.getUser().getId()))
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
