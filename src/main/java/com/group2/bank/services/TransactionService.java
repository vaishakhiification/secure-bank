package com.group2.bank.services;

import com.group2.bank.models.User;
import com.group2.bank.repositories.UserRepository;
import com.group2.bank.resources.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;


@Service
public class TransactionService {

    @Autowired
    UserRepository userRepository;

    public Response deposit(String username, String password, Double depositAmt) {

        //Check and validate username and password
        String regex = "[_\\-\\.0-9a-z]+";
        if(!Pattern.matches(regex,username)){
            return new Response(409,"invalid username");
        }
        if(!Pattern.matches(regex,password)){
            return new Response(409,"invalid password");
        }

        String checkBalanceValidity = String.valueOf(depositAmt);
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

        User user = userRepository.findByUserName(username);

        if(user == null){
            return new Response(409,"User does not exist");
        }
        else if(!user.getPassword().equals(password)){
            return new Response(409,"Incorrect password");
        }



        //if everything is correct then deposit
        user.setBalance(depositAmt);
        userRepository.save(user);

        return new Response(200,"Amount Deposited in the user account");

    }

    public Response withdraw(String username, String password, Double withdrawAmt){

        //Check and validate username and password
        String regex = "[_\\-\\.0-9a-z]+";
        if(!Pattern.matches(regex,username)){
            return new Response(409,"invalid username");
        }
        if(!Pattern.matches(regex,password)){
            return new Response(409,"invalid password");
        }

        String checkBalanceValidity = String.valueOf(withdrawAmt);
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

        User user = userRepository.findByUserName(username);

        if(user == null){
            return new Response(409,"User does not exist");
        }
        else if(!user.getPassword().equals(password)){
            return new Response(409,"Incorrect password");
        }



        //if everything is correct then deposit

        Double amountToWithdraw = (withdrawAmt * -1);
        user.setBalance(amountToWithdraw);
        userRepository.save(user);

        return new Response(200,"Amount withdrawn");

    }
}
