package com.group2.bank.services;

import com.group2.bank.models.User;
import com.group2.bank.repositories.UserRepository;
import com.group2.bank.resources.Response;
import com.group2.bank.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 * This service takes care of operations
 * like depositing and withdrawing amounts from bank
 * All services interact with the repository in some way
 * maybe for validations or updations
 */
@Service
public class TransactionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Session session;

    public Response deposit(String username, String password, Double depositAmt) {

        if (!session.isLoggedIn()) {
            return new Response(409, "User not logged in");
        }

        //Check and validate username and password
        String regex = "[_\\-\\.0-9a-z]+";
        if (!Pattern.matches(regex, username)) {
            return new Response(409, "invalid username");
        }
        if (!Pattern.matches(regex, password)) {
            return new Response(409, "invalid password");
        }

        String checkBalanceValidity = String.valueOf(depositAmt);
        String nonFractionalPart;
        if (checkBalanceValidity.contains(".")) {
            String[] numberSplit = checkBalanceValidity.split("\\.");
            nonFractionalPart = numberSplit[0];
        } else {
            nonFractionalPart = checkBalanceValidity;
        }
        if (!(nonFractionalPart.length() > 0) || !(nonFractionalPart.length() < 5)) {
            return new Response(409, "please enter an amount between 0 and 9999");
        }

        //checking for the currency validation along with fractional value
        //normalizing it into decimal form first
        String validBalance = checkBalanceValidity;
        if (!checkBalanceValidity.contains(".")) {
            validBalance = validBalance + ".00";
        } else {
            String[] tokens = validBalance.split("\\.");
            if (!validBalance.endsWith(".") && tokens[1].length() == 1) {
                tokens[1] = tokens[1] + "0";
                validBalance = tokens[0] + "." + tokens[1];
            }
        }

        //checking for the currency validation
        regex = "0|[1-9][0-9]*[\\.]{0,1}[0-9]{2}";
        if (!Pattern.matches(regex, validBalance)) {
            return new Response(409, "please enter a valid balance value");
        }

        User user = userRepository.findByUserName(username);

        if (user == null) {
            return new Response(409, "User does not exist");
        } else if (!user.getPassword().equals(password)) {
            return new Response(409, "Incorrect password");
        }

        //if everything is correct then deposit
        //double balance = user.getBalance();
        user.setBalance(true, depositAmt);
        userRepository.save(user);

        return new Response(200, "Amount Deposited in the user account");

    }

    public Response withdraw(String username, String password, Double withdrawAmt) {

        if (!session.isLoggedIn()) {
            return new Response(409, "User not logged in");
        }

        //Check and validate username and password
        String regex = "[_\\-\\.0-9a-z]+";
        if (!Pattern.matches(regex, username)) {
            return new Response(409, "invalid username");
        }
        if (!Pattern.matches(regex, password)) {
            return new Response(409, "invalid password");
        }

        String checkBalanceValidity = String.valueOf(withdrawAmt);
        String nonFractionalPart;
        if (checkBalanceValidity.contains(".")) {
            String[] numberSplit = checkBalanceValidity.split("\\.");
            nonFractionalPart = numberSplit[0];
        } else {
            nonFractionalPart = checkBalanceValidity;
        }
        if (!(nonFractionalPart.length() > 0) || !(nonFractionalPart.length() < 5)) {
            return new Response(409, "please enter an amount between 0 and 9999");
        }

        //checking for the currency validation along with fractional value
        //normalizing it into decimal form first
        String validBalance = checkBalanceValidity;
        if (!checkBalanceValidity.contains(".")) {
            validBalance = validBalance + ".00";
        } else {
            String[] tokens = validBalance.split("\\.");
            if (!validBalance.endsWith(".") && tokens[1].length() == 1) {
                tokens[1] = tokens[1] + "0";
                validBalance = tokens[0] + "." + tokens[1];
            }
        }

        //checking for the currency validation
        regex = "0|[1-9][0-9]*[\\.]{0,1}[0-9]{2}";
        if (!Pattern.matches(regex, validBalance)) {
            return new Response(409, "please enter a valid balance value");
        }

        User user = userRepository.findByUserName(username);

        if (user == null) {
            return new Response(409, "User does not exist");
        } else if (!user.getPassword().equals(password)) {
            return new Response(409, "Incorrect password");
        }


        //if everything is correct then withdraw


        double balance = user.getBalance();
        if (balance - withdrawAmt < 0) {
            return new Response(409, "Not enough funds");
        }
        //double newBalance = balance - withdrawAmt;
        user.setBalance(false, withdrawAmt);
        userRepository.save(user);

        return new Response(200, "Amount withdrawn");

    }
}
