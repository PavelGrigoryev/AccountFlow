package com.grigoryev.accountflow.job;

import com.grigoryev.accountflow.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountBalanceJob {

    private final AccountRepository accountRepository;
    private final Map<Long, BigDecimal> initialDeposits;

    @Value("${balance.increase-multiplier}")
    private BigDecimal increaseMultiplier;

    @Value("${balance.max-multiplier}")
    private BigDecimal maxMultiplier;

    @Transactional
    @Scheduled(fixedRateString = "${balance.fixed-rate}", initialDelayString = "${balance.initial-delay}")
    public void increaseBalances() {
        accountRepository.findAll()
                .parallelStream()
                .forEach(account -> {
                    Long accountId = account.getId();
                    BigDecimal balance = account.getBalance();
                    BigDecimal initialDeposit = initialDeposits.computeIfAbsent(accountId, k -> balance);
                    BigDecimal newBalance = balance.multiply(increaseMultiplier).setScale(2, RoundingMode.HALF_UP);
                    BigDecimal maxBalance = initialDeposit.multiply(maxMultiplier).setScale(2, RoundingMode.HALF_UP);

                    if (balance.compareTo(maxBalance) >= 0) {
                        log.info("Balance for account with id = {} already at max: {}", accountId, balance);
                        return;
                    }

                    newBalance = newBalance.compareTo(maxBalance) > 0 ? maxBalance : newBalance;
                    account.setBalance(newBalance);
                    log.info("Updating balance for account with id = {}: {} -> {}", accountId, balance, newBalance);
                });
    }

}
