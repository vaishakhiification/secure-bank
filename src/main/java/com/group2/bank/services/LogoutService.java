package com.group2.bank.services;


import com.group2.bank.resources.Response;
import com.group2.bank.resources.UserResponse;
import com.group2.bank.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    @Autowired
    Session session;

    public Response logout() {

        if(session.isLoggedIn()){
            session.setLoggedIn(false);
        }
        return new Response(200,"Logged Out");

    }
}
