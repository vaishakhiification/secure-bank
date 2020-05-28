package com.group2.bank.session;

import org.springframework.stereotype.Component;

/**
 * Since we are assuming that if we maintain sessions, our program would be a bit more complex
 * We have created this session class to mimic a session
 * The boolean flag represents the state of user, if they are logged in or not
 */
@Component
public class Session {

    boolean loggedIn;

    Session() {
        this.loggedIn = false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
