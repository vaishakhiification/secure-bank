package com.group2.bank.models;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private double balance;

    public User() {
    }

    public User(String firstName, String lastName, String username, String password, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = username;
        this.password = password;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(Double depositAmount){
        balanceSetter(depositAmount);
    }

    private void balanceSetter(Double depositAmount){

        double newAmount = this.balance + depositAmount;
        this.balance = newAmount;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + userName + '\'' +
                ", balance=" + balance +
                '}';
    }


}
