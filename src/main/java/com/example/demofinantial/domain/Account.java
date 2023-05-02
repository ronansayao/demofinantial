package com.example.demofinantial.domain;

import com.example.demofinantial.domain.dto.AccountDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class Account {
    private Integer userId;
    private String firstName;
    private String lastName;
    private String routingNumber;
    private String nationalIdentificationNumber;
    private String accountNumber;

    public Account(AccountDTO accountDTO) {
        this.firstName = accountDTO.getFirstName();
        this.lastName = accountDTO.getLastName();
        this.routingNumber = accountDTO.getRoutingNumber();
        this.nationalIdentificationNumber = accountDTO.getNationalIdentificationNumber();
        this.accountNumber = accountDTO.getAccountNumber();

    }
}
