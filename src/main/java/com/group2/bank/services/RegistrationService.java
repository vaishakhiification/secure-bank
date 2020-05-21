package com.group2.bank.services;

import com.group2.bank.models.User;
import com.group2.bank.repositories.UserRepository;
import com.group2.bank.resources.Response;
import org.springframework.stereotype.Component;

@Component
public class RegistrationService {

    UserRepository userRepository;
    Response response;

    public  Response createUserAccount(String firstName, String lastName, String userName, String passWord, Double balance){

        //this method will do all server side validations

        //validating a userName, do not allow to create if the username is already taken

        User user = userRepository.findByUserName(userName);

        if(user != null){
            //Username occupied
            return new Response(409,"user name already taken");
        }


        //TODO All the validations are remaining
        //all if conditions and if everything is good the following block saves this account
        user = new User(firstName,lastName,userName,passWord,balance);
        userRepository.save(user);
        return new Response(200,"user account created");
    }
}
