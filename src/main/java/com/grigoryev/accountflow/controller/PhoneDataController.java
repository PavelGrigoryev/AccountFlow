package com.grigoryev.accountflow.controller;

import com.grigoryev.accountflow.aspect.Loggable;
import com.grigoryev.accountflow.dto.DeleteResponse;
import com.grigoryev.accountflow.dto.phone.PhoneDataRequest;
import com.grigoryev.accountflow.dto.phone.PhoneDataResponse;
import com.grigoryev.accountflow.service.PhoneDataService;
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
@RequestMapping("/phones")
public class PhoneDataController {

    private final PhoneDataService phoneDataService;

    @GetMapping("/{id}")
    public ResponseEntity<PhoneDataResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(phoneDataService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PhoneDataResponse> save(@RequestBody @Valid PhoneDataRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(phoneDataService.save(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PhoneDataResponse> update(@PathVariable Long id, @Valid @RequestBody PhoneDataRequest request) {
        return ResponseEntity.ok(phoneDataService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(phoneDataService.delete(id));
    }

}
