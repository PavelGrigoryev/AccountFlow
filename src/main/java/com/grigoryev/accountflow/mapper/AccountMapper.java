package com.grigoryev.accountflow.mapper;

import com.grigoryev.accountflow.dto.account.AccountResponse;
import com.grigoryev.accountflow.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final UserMapper userMapper;

    public AccountResponse toAccountResponse(Account account) {
        return new AccountResponse(account.getId(), account.getBalance(), userMapper.toUserResponse(account.getUser()));
    }

}
