package com.group2.bank.models;



import javax.persistence.*;

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

    public User() {
    }

    public User(String firstName, String lastName, String userName, String password, double balance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(boolean deposit, Double transactionAmount){
        double newAmount = 0.00;
        if(deposit == true){
            newAmount = this.balance + transactionAmount;
        }
        else{
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
                ", username='" + userName + '\'' +
                ", balance=" + balance +
                '}';
    }


}
