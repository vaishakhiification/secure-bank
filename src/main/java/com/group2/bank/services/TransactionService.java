package com.group2.bank.services;

import com.group2.bank.models.User;
import com.group2.bank.repositories.UserRepository;
import com.group2.bank.resources.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TransactionService {

    @Autowired
    UserRepository userRepository;

    public Response deposit(String username, String password, Double depositAmt) {

        User user = userRepository.findByUserName(username);

        if(user == null){
            return new Response(409,"User does not exist");
        }
        else if(!user.getPassword().equals(password)){
            return new Response(409,"Incorrect password");
        }

        //TODO validations on deposit Amt

        //if everything is correct then deposit
        user.setBalance(depositAmt);
        userRepository.save(user);

        return new Response(200,"Amount Deposited in the user account");

    }

    public Response withdraw(String username, String password, Double withdrawAmt){
        User user = userRepository.findByUserName(username);

        if(user == null){
            return new Response(409,"User does not exist");
        }
        else if(!user.getPassword().equals(password)){
            return new Response(409,"Incorrect password");
        }

        //TODO validations on deposit Amt

        //if everything is correct then deposit

        Double amountToWithdraw = (withdrawAmt * -1);
        user.setBalance(amountToWithdraw);
        userRepository.save(user);

        return new Response(200,"Amount withdrawn");

    }
}
