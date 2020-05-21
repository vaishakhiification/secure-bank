package com.group2.bank.controllers;

import com.group2.bank.models.User;
import com.group2.bank.resources.Response;
import com.group2.bank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/deposit")
    public Response deposit(@RequestBody User user, @RequestBody String depositAmount){

        String username = user.getUsername();
        String password = user.getPassword();
        Double depositAmt = Double.parseDouble(depositAmount);

        Response response = transactionService.deposit(username,password,depositAmt);
        return response;
    }

    @PostMapping("/withdraw")
    public Response withdraw(@RequestBody User user, @RequestBody String withdrawAmount){
        String username = user.getUsername();
        String password = user.getPassword();
        Double withdrawAmt = Double.parseDouble(withdrawAmount);

        Response response = transactionService.deposit(username,password,withdrawAmt);
        return response;

    }
}
