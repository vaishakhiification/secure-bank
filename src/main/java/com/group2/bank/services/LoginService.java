package com.group2.bank.services;

import com.group2.bank.models.User;
import com.group2.bank.repositories.UserRepository;
import com.group2.bank.resources.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    public Response login(String userName, String password){

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



        String userAsResponse = user.toString();
        return new Response(200,userAsResponse);
    }
}
