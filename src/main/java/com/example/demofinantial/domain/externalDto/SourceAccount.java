package com.example.demofinantial.domain.externalDto;

import lombok.Builder;
import lombok.Setter;

@Builder
@Setter
public class SourceAccount {
    private String type;
    private SourceInformation sourceInformation;
    private CommonAccount account;
}
