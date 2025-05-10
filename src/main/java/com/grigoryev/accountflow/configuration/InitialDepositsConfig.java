package com.grigoryev.accountflow.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class InitialDepositsConfig {

    @Bean
    public Map<Long, BigDecimal> initialDeposits() {
        return new ConcurrentHashMap<>();
    }

}
