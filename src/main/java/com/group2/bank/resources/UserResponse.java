package com.group2.bank.resources;

import com.group2.bank.models.User;

public class UserResponse {
    private long statusCode;

    private User user;

    public UserResponse(long statusCode, User user) {
        this.statusCode = statusCode;
        this.user = user;
    }

    public long getStatusCode() {
        return statusCode;
    }

    public User getUser() {
        return user;
    }
}
