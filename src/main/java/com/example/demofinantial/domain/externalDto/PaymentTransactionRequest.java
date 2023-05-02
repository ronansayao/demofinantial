package com.example.demofinantial.domain.externalDto;

import lombok.Builder;

@Builder
public class PaymentTransactionRequest {
    private SourceAccount source;
    private DestinationAccount destination;
    private Double amount;
}
