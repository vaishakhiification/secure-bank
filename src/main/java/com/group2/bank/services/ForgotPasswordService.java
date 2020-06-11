package com.group2.bank.services;

import com.group2.bank.models.User;
import com.group2.bank.repositories.UserRepository;
import com.group2.bank.resources.ForgotPasswordCounter;
import com.group2.bank.resources.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * The forgot password service takes care of resetting the user password
 * This service performs all validation checks
 * and returns a Response object
 */
@Service
public class ForgotPasswordService {

    @Autowired
    UserRepository userRepository;

   public Response resetPassword(String userName, String securityAnswer, String newPassword) {

        if(forgotPasswordCounter.getCounter() >= 3){
            return new Response(409,"Exceeded number of tries! Your account has been blocked!");
        }
        forgotPasswordCounter.setCounter(forgotPasswordCounter.getCounter() + 1);
        //Check and validate username and password
        String regex = "[_\\-\\.0-9a-z]+";
        if (!Pattern.matches(regex, userName)) {
            return new Response(409, "invalid username entered");
        }
        if (!Pattern.matches(regex, newPassword)) {
            return new Response(409, "invalid new Password entered");
        }
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return new Response(409, "User doesn't exist");
        }
        if (!securityAnswer.equalsIgnoreCase(user.getSecurityAns())) {
            return new Response(409, "incorrect security answer");
        }
        user.setPassword(newPassword);
        userRepository.save(user);
        return new Response(200, "new password has been reset");
    }
}
