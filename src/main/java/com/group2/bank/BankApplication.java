package com.group2.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
public class BankApplication {


    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }
}
