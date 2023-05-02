package com.example.demofinantial.domain.externalDto;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class CommonAccount {
    private String accountNumber;
    private String currency;
    private String routingNumber;
}
