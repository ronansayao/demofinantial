package com.example.demofinantial.infrastructure.adapters.entities;

import com.example.demofinantial.domain.Account;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer userId;
    private String firstName;
    private String lastName;
    private String routingNumber;
    private String nationalIdentificationNumber;
    private String accountNumber;

    public AccountEntity(){

    }

    public UUID getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    public String getNationalIdentificationNumber() {
        return nationalIdentificationNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    public AccountEntity(Account account, Integer userId) {
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.routingNumber = account.getRoutingNumber();
        this.nationalIdentificationNumber = account.getNationalIdentificationNumber();
        this.accountNumber = account.getAccountNumber();
        this.userId = account.getUserId();
    }

    public Account toAccount() {
        return  Account.builder().firstName(this.firstName)
                .lastName(this.lastName)
                .routingNumber(this.routingNumber)
                .nationalIdentificationNumber(this.nationalIdentificationNumber)
                .accountNumber(this.accountNumber)
                .userId(this.userId).build();
    }

}
