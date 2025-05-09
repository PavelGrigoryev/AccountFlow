package com.grigoryev.accountflow.controller;

import com.grigoryev.accountflow.dto.phone.PhoneDataRequest;
import com.grigoryev.accountflow.dto.phone.PhoneDataResponse;
import com.grigoryev.accountflow.service.PhoneDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/phones")
public class PhoneDataController {

    private final PhoneDataService phoneDataService;

    @PostMapping
    public ResponseEntity<PhoneDataResponse> save(@RequestBody PhoneDataRequest request) {
        return ResponseEntity.ok(phoneDataService.save(request));
    }

}
