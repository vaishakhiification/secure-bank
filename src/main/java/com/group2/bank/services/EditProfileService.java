package com.group2.bank.services;

import com.group2.bank.models.User;
import com.group2.bank.repositories.UserRepository;
import com.group2.bank.resources.Response;
import com.group2.bank.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * This service takes care of editing the user profile in the system
 * This service performs all validation checks
 */
@Service
public class EditProfileService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Session session;

    public Response editProfile(String username, String password, String newPassword, String newFirstName, String newLastName, String newSecurityAnswer) {
        //this method will do all server side validations

        //validating a userName, do not allow to create if the username is already taken
        if (!session.isLoggedIn()) {
            return new Response(409, "User not logged in");
        }

        User user = userRepository.findByUserName(username);

        //Check and validate username and password
        String regex = "[_\\-\\.0-9a-z]+";
        if (!Pattern.matches(regex, username)) {
            return new Response(409, "invalid username");
        }
        if (!Pattern.matches(regex, password)) {
            return new Response(409, "invalid password");
        }

        if (!Pattern.matches(regex, newPassword)) {
            return new Response(409, "invalid new password");
        }

        if (user == null) {
            return new Response(409, "User does not exist");
        } else if (!user.getPassword().equals(password)) {
            return new Response(409, "Incorrect password");
        }

        regex = "[a-zA-Z]+";
        //name we only allow alphabetic chars
        if (!Pattern.matches(regex, newLastName)) {
            return new Response(409, "please enter a valid lastname");
        }

        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        user.setPassword(newPassword);
        user.setSecurityAns(newSecurityAnswer);

        userRepository.save(user);
        return new Response(200, "user profile edited");

    }
}
