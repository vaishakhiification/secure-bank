package com.group2.bank.services;

import com.group2.bank.models.User;
import com.group2.bank.repositories.UserRepository;
import com.group2.bank.resources.Response;
import com.group2.bank.resources.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ForgotPasswordService {

    @Autowired
    UserRepository userRepository;

    public Response resetPassword(String userName, String password, String securityAnswer) {

        //Check and validate username and password
        String regex = "[_\\-\\.0-9a-z]+";
        if(!Pattern.matches(regex,userName)){
            return new Response(409,"invalid username entered");

        }
        if(!Pattern.matches(regex,password)){
            return new Response(409,"invalid password entered");

        }

        User user = userRepository.findByUserName(userName);

        if(user == null){
            return new Response(409,"User doesn't exist");

        }
        else if(!user.getPassword().equals(password)){
            return new Response(409,"Password is incorrect");

        }

        return new Response(200,"new password has been sent to email, change it as soon as possible");

    }
}
