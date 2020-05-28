package com.group2.bank.controllers;

import com.group2.bank.models.User;
import com.group2.bank.resources.Response;
import com.group2.bank.services.EditProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller for handling post edit profile requests
 * Annotated with CORS to prevent Cross Origin Errors
 * The client sends a user object which contains username
 * This controller comes into action when client needs to eit their profile
 */


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EditProfileController {


    @Autowired
    EditProfileService editProfileService;

    @PostMapping("/editProfile")
    public Response editProfile(@RequestBody User user){

        String username = user.getUserName();
        String password = user.getPassword();
        String newPassword = user.getNewPassword();
        String newFirstName = user.getFirstName();
        String newLastName = user.getLastName();
        String newSecurityAnswer = user.getSecurityAns();
        Response response = editProfileService.editProfile(username,password,newPassword,newFirstName,newLastName,newSecurityAnswer);
        return response;
    }

}
