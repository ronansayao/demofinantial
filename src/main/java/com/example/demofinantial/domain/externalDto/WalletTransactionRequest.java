package com.example.demofinantial.domain.externalDto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class WalletTransactionRequest {
    private Double amount;

    @JsonProperty("user_id")
    private Integer userId;
}
