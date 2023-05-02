package com.example.demofinantial.domain.externalDto;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class DestinationAccount {
    private String name;
    private CommonAccount account;
}
