package com.group2.bank.controllers;

import com.group2.bank.models.Transactions;
import com.group2.bank.models.User;
import com.group2.bank.resources.Response;
import com.group2.bank.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @PostMapping("/deposit")
    public Response deposit(@RequestBody Transactions transaction){

        User user = transaction.getUser();
        String username = user.getUserName();
        String password = user.getPassword();
        Double depositAmt = transaction.getAmount();

        Response response = transactionService.deposit(username,password,depositAmt);
        return response;
    }

    @PostMapping("/withdraw")
    public Response withdraw(@RequestBody Transactions transaction){
        User user = transaction.getUser();
        String username = user.getUserName();
        String password = user.getPassword();
        Double withdrawAmt = transaction.getAmount();

        Response response = transactionService.deposit(username,password,withdrawAmt);
        return response;

    }
}
