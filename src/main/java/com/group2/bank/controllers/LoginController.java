package com.group2.bank.controllers;

import com.group2.bank.models.User;
import com.group2.bank.resources.Response;
import com.group2.bank.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public Response login(@RequestBody User user){

        String userName = user.getUsername();
        String password = user.getPassword();
        Response response = loginService.login(userName,password);
        return response;

    }
}
