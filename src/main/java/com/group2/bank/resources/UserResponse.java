package com.group2.bank.resources;

import com.group2.bank.models.User;

/**
 * This class is created wherever there is a need to send
 * the whole user object back to the client
 */
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
