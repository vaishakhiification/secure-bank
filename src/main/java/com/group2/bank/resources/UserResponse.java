package com.group2.bank.resources;

import com.group2.bank.models.User;

public class UserResponse extends Response{
    private User user;

    public UserResponse(long statusCode, String responseMessage, User user) {
        super(statusCode, responseMessage);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
