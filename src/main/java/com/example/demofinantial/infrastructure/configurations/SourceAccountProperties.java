package com.example.demofinantial.infrastructure.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class SourceAccountProperties {
    @Value("${source.account.type}")
    private String type;

    @Value("${source.account.name}")
    private String name;

    @Value("${source.account.accountNumber}")
    private String accountNumber;

    @Value("${source.account.routingNumber}")
    private String routingNumber;
}
