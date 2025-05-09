package com.grigoryev.accountflow;

import org.springframework.boot.SpringApplication;

public class TestAccountFlowApplication {

    public static void main(String[] args) {
        SpringApplication.from(AccountFlowApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
