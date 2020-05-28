package com.group2.bank.controllers;

import com.group2.bank.models.User;
import com.group2.bank.resources.Response;
import com.group2.bank.resources.UserResponse;
import com.group2.bank.services.LoginService;
import com.group2.bank.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public UserResponse login(@RequestBody User user){

        String userName = user.getUserName();
        String password = user.getPassword();
        UserResponse response = loginService.login(userName,password);

        return response;

    }
}
