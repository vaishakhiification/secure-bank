package com.group2.bank.controllers;

import com.group2.bank.models.User;
import com.group2.bank.resources.Response;
import com.group2.bank.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/register")
    public Response register(@RequestBody User user) {

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String username = user.getUserName();
        String password = user.getPassword();
        Double balance = user.getBalance();
        String securityAns = user.getSecurityAns();

        Response response = registrationService.createUserAccount(firstName, lastName, username, password, balance,securityAns);

        return response;
    }

}
