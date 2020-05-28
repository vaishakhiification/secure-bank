package com.group2.bank.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The Transaction Controller requires an object of this type
 * and thus this class was created for convenience
 */
public class Transactions {

    @Autowired
    User user;

    //This represents the transaction amount
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
