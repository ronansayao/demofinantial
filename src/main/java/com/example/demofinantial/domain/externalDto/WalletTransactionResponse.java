package com.example.demofinantial.domain.externalDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WalletTransactionResponse {
    private Double amount;

    @JsonProperty("wallet_transaction_id")
    private Integer walletTransactionId;

    @JsonProperty("user_id")
    private Integer userId;
}
