package com.group2.bank.models;

import javax.persistence.*;


/**
 * This is the User model. It is an entity
 * The sqlite database bank0.db has a table mapped to this called user
 * Each field in this model represents a column
 * The annotations show column names as in the database
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "balance")
    private double balance;

    @Column(name = "securityans")
    private String securityAns;

    public User() {
    }

    public User(String firstName, String lastName, String userName, String password, double balance, String securityAns) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.balance = balance;
        this.securityAns = securityAns;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public String getSecurityAns() {
        return securityAns;
    }

    //This setter method is helpful when the
    //user has forgotten the password
    //and wants to change it using reset password option
    public void setSecurityAns(String securityAns) {
        this.securityAns = securityAns;
    }

    //This method is used when the user is trying to make a transaction
    //boolean deposit field is true, if the transaction is a deposit
    //and false if it is a withdrawal
    public void setBalance(boolean deposit, Double transactionAmount) {

        double newAmount = 0.00;
        if (deposit == true) {
            newAmount = this.balance + transactionAmount;
        } else {
            newAmount = this.balance - transactionAmount;
        }
        this.balance = newAmount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", securityAns='" + securityAns + '\'' +
                '}';
    }
}
