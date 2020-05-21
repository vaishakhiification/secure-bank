package com.group2.bank.controllers;

import com.group2.bank.models.User;
import com.group2.bank.resources.Response;
import com.group2.bank.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/register")
    public Response register(@RequestBody User user){

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String userName = user.getUsername();
        String password = user.getPassword();
        Double balance = user.getBalance();

        Response response = registrationService.createUserAccount(firstName,lastName,userName,password,balance);

        return response;
    }

}
