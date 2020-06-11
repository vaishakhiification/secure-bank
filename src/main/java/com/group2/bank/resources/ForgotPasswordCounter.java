package com.group2.bank.resources;

import org.springframework.stereotype.Component;

@Component
public class ForgotPasswordCounter {

    private Integer counter = 0;

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
