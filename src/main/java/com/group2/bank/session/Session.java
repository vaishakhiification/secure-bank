package com.group2.bank.session;

import org.springframework.stereotype.Component;

@Component
public class Session {

    boolean loggedIn;

    Session(){
        this.loggedIn = false;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
