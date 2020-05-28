package com.group2.bank.services;

import com.group2.bank.models.User;
import com.group2.bank.repositories.UserRepository;
import com.group2.bank.resources.Response;
import com.group2.bank.resources.UserResponse;
import com.group2.bank.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Session session;

    public UserResponse login(String userName, String password){

        //Check and validate username and password
        String regex = "[_\\-\\.0-9a-z]+";
        if(!Pattern.matches(regex,userName)){
            //return new UserResponse(409,"invalid username entered");
            return new UserResponse(409,"invalid username entered",null);
        }
        if(!Pattern.matches(regex,password)){
            //return new Response(409,"invalid password entered");
            return new UserResponse(409,"invalid password entered",null);
        }

        User user = userRepository.findByUserName(userName);

        if(user == null){
            //return new Response(409,"User doesn't exist");
            return new UserResponse(409,"User doesn't exist",null);
        }
        else if(!user.getPassword().equals(password)){
            //return new Response(409,"Password is incorrect");
            return new UserResponse(409,"Password is incorrect",null);
        }



        //String userAsResponse = user.toString();

        session.setLoggedIn(true);
        return new UserResponse(200,"User created",user);
    }
}
