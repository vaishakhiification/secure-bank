package com.group2.bank.services;

import com.group2.bank.models.User;
import com.group2.bank.repositories.UserRepository;
import com.group2.bank.resources.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class RegistrationService {

    @Autowired
    UserRepository userRepository;

    public  Response createUserAccount(String firstName, String lastName, String userName, String password, Double balance){

        //this method will do all server side validations

        //validating a userName, do not allow to create if the username is already taken

        User user = userRepository.findByUserName(userName);

        if(user != null){
            //Username occupied
            return new Response(409,"user name already taken");
        }

        //checking names and passwords for alphanumerics
        //checking the lengths
        if(firstName.length() > 127 || lastName.length() > 127 || userName.length() > 127 || password.length() > 127){
            return new Response(409,"max length should be 127 characters");
        }
        else if(firstName.length() <= 0 || lastName.length() <= 0 || userName.length() <= 0 || password.length() <= 0){
            return new Response(409,"field is empty");
        }

        //Check and validate username and password
        String regex = "[_\\-\\.0-9a-z]+";

        if(!Pattern.matches(regex,userName)){
            return new Response(409,"invalid username entered");
        }
        if(!Pattern.matches(regex,password)){
            return new Response(409,"invalid password entered");
        }

        regex = "[a-zA-Z]+";
        //For first name and last name we only allow alphabetic chars
        if(!Pattern.matches(regex,firstName)){
            return new Response(409,"please enter a valid firstname");
        }
        if(!Pattern.matches(regex,lastName)){
            return new Response(409,"please enter a valid lastname");
        }

        String checkBalanceValidity = String.valueOf(balance);
        String nonFractionalPart;
        if(checkBalanceValidity.contains(".")){
            String[] numberSplit = checkBalanceValidity.split("\\.");
            nonFractionalPart = numberSplit[0];
        }
        else{
            nonFractionalPart = checkBalanceValidity;
        }
        if(!(nonFractionalPart.length() > 0) || !(nonFractionalPart.length() < 5)){
            return new Response(409,"please enter an input(max amount: 99999");
        }

        //checking for the currency validation
        regex = "0|[1-9][0-9]*[\\.]{0,1}[0-9]{2}";
        if(!Pattern.matches(regex,checkBalanceValidity)){
            return new Response(409,"please enter an input(max amount: 99999");
        }
        //all if conditions and if everything is good the following block saves this account
        user = new User(firstName,lastName,userName,password,balance);
        userRepository.save(user);
        return new Response(200,"user account created");
    }
}
