package com.example.demofinantial.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Transaction {
    private UUID id;
    private Integer userId;
    private Status status;
    private String message;
    private UUID paymentTransactionId;
    private Integer walletTransactionId;
    private Double amount;
    private Double fee;
    private LocalDateTime createdAt;

}
