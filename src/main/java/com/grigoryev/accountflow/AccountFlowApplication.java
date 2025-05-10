package com.grigoryev.accountflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AccountFlowApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountFlowApplication.class, args);
    }

}
