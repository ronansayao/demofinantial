package com.example.demofinantial.domain.externalDto;

import lombok.Getter;

import java.util.UUID;
@Getter
public class PaymentInformation {
    private Double amount;
    private UUID id;
}
