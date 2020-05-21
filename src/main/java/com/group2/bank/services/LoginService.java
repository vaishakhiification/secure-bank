package com.group2.bank.services;

import com.group2.bank.models.User;
import com.group2.bank.repositories.UserRepository;
import com.group2.bank.resources.Response;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    UserRepository userRepository;
    public Response login(String userName, String password){

        User user = userRepository.findByUserName(userName);

        if(user == null){
            return new Response(409,"User doesn't exist");
        }
        else if(!user.getPassword().equals(password)){
            return new Response(409,"Password is incorrect");
        }
        //TODO think of more validations

        String userAsResponse = user.toString();
        return new Response(200,userAsResponse);
    }
}
