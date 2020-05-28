package com.group2.bank.services;

import com.group2.bank.models.User;
import com.group2.bank.repositories.UserRepository;
import com.group2.bank.resources.Response;
import com.group2.bank.resources.UserResponse;
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

        //Check and validate username and password
        String regex = "[_\\-\\.0-9a-z]+";
        if(!Pattern.matches(regex,userName)){
            return new Response(409,"invalid username entered");

        }
        if(!Pattern.matches(regex,newPassword)){
            return new Response(409,"invalid new Password entered");

        }

        User user = userRepository.findByUserName(userName);

        if(user == null){
            return new Response(409,"User doesn't exist");

        }

        if(!securityAnswer.equals(user.getSecurityAns())){
            return new Response(409,"incorrect security answer");
        }
        user.setSecurityAns(securityAnswer);
        userRepository.save(user);
        return new Response(200,"new password has been reset");

    }
}
