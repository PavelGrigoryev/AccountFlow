package com.grigoryev.accountflow.service;

import com.grigoryev.accountflow.dto.account.AccountResponse;
import com.grigoryev.accountflow.exception.AccountNotFoundException;
import com.grigoryev.accountflow.mapper.AccountMapper;
import com.grigoryev.accountflow.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Transactional(readOnly = true)
    @Cacheable(value = "accounts", key = "#id")
    public AccountResponse findWithUserById(Long id) {
        return accountRepository.findWithUserById(id)
                .map(accountMapper::toAccountResponse)
                .orElseThrow(() -> new AccountNotFoundException("Account with id = %d is not found".formatted(id)));
    }

}
