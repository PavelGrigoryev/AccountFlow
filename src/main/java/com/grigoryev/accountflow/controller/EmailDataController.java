package com.grigoryev.accountflow.controller;

import com.grigoryev.accountflow.dto.email.EmailDataRequest;
import com.grigoryev.accountflow.dto.email.EmailDataResponse;
import com.grigoryev.accountflow.service.EmailDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emails")
public class EmailDataController {

    private final EmailDataService emailDataService;

    @PostMapping
    public ResponseEntity<EmailDataResponse> save(@RequestBody EmailDataRequest request) {
        return ResponseEntity.ok(emailDataService.save(request));
    }

}
