package com.example.demofinantial.domain.dto;


import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class AccountDTO implements Serializable {
    private String firstName;
    private String lastName;
    private String routingNumber;
    private String nationalIdentificationNumber;
    private String accountNumber;
    private Integer userId;
}
