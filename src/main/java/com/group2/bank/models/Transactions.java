package com.group2.bank.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class Transactions {

    @Autowired
    User user;

    Double amount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getAmount() {
        return amount;
    }

}
