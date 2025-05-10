package com.grigoryev.accountflow.service;

import com.grigoryev.accountflow.dto.account.AccountResponse;
import com.grigoryev.accountflow.dto.account.TransferRequest;
import com.grigoryev.accountflow.dto.account.TransferResponse;
import com.grigoryev.accountflow.exception.AccountNotFoundException;
import com.grigoryev.accountflow.exception.TransferFundsException;
import com.grigoryev.accountflow.interceptor.UserHolder;
import com.grigoryev.accountflow.mapper.AccountMapper;
import com.grigoryev.accountflow.model.Account;
import com.grigoryev.accountflow.model.User;
import com.grigoryev.accountflow.repository.AccountRepository;
import com.grigoryev.accountflow.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccountMapper accountMapper;
    private final UserHolder userHolder;

    @Transactional(readOnly = true)
    public AccountResponse findWithUserById(Long id) {
        return accountRepository.findWithUserById(id)
                .map(accountMapper::toAccountResponse)
                .orElseThrow(() -> new AccountNotFoundException("Account with id = %d is not found".formatted(id)));
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TransferResponse transferMoney(TransferRequest request) {
        User user = userHolder.getUser();

        Long fromUserId = user.getId();
        Long toUserId = request.toUserId();
        BigDecimal amount = request.amount();

        validateTransfer(fromUserId, toUserId);

        Account fromAccount = accountRepository.findWithLockByUserId(fromUserId)
                .orElseThrow(() -> new AccountNotFoundException("Sender account with id = %d is not found".formatted(fromUserId)));
        Account toAccount = accountRepository.findWithLockByUserId(toUserId)
                .orElseThrow(() -> new AccountNotFoundException("Recipient account with id = %d is not found".formatted(toUserId)));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new TransferFundsException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        return new TransferResponse("Transfer successful");
    }

    private void validateTransfer(Long fromUserId, Long toUserId) {
        if (fromUserId.equals(toUserId)) {
            throw new TransferFundsException("Cannot transfer to the same user");
        }
        if (!userRepository.existsById(toUserId)) {
            throw new TransferFundsException("Recipient user is not found");
        }
    }

}
