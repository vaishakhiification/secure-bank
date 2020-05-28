package com.group2.bank.controllers;

import com.group2.bank.models.User;
import com.group2.bank.resources.UserResponse;
import com.group2.bank.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * Rest Controller for handling post login requests
 * Annotated with CORS to prevent Cross Origin Errors
 * The client sends a username and password in the body
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public UserResponse login(@RequestBody User user) {

        String userName = user.getUserName();
        String password = user.getPassword();
        UserResponse response = loginService.login(userName, password);

        return response;

    }
}
