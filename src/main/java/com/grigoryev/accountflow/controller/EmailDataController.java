package com.grigoryev.accountflow.controller;

import com.grigoryev.accountflow.aspect.Loggable;
import com.grigoryev.accountflow.controller.openapi.EmailDataOpenApi;
import com.grigoryev.accountflow.dto.DeleteResponse;
import com.grigoryev.accountflow.dto.email.EmailDataRequest;
import com.grigoryev.accountflow.dto.email.EmailDataResponse;
import com.grigoryev.accountflow.service.EmailDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Loggable
@RestController
@RequiredArgsConstructor
@RequestMapping("/emails")
public class EmailDataController implements EmailDataOpenApi {

    private final EmailDataService emailDataService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EmailDataResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(emailDataService.findById(id));
    }

    @Override
    @PostMapping
    public ResponseEntity<EmailDataResponse> save(@RequestBody @Valid EmailDataRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(emailDataService.save(request));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<EmailDataResponse> update(@PathVariable Long id, @RequestBody @Valid EmailDataRequest request) {
        return ResponseEntity.ok(emailDataService.update(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(emailDataService.delete(id));
    }

}
