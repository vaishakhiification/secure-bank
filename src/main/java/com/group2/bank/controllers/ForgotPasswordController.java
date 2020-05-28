package com.group2.bank.controllers;

import com.group2.bank.models.User;
import com.group2.bank.resources.Response;
import com.group2.bank.services.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for handling post forgot password service requests
 * Annotated with CORS to prevent Cross Origin Errors
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ForgotPasswordController {

    @Autowired
    ForgotPasswordService forgotPasswordService;

    @PostMapping("/resetPassword")
    public Response resetPassword(@RequestBody User user) {

        String userName = user.getUserName();
        String password = user.getPassword();
        String securityAnswer = user.getSecurityAns();
        Response response = forgotPasswordService.resetPassword(userName, securityAnswer, password);

        return response;

    }

}
