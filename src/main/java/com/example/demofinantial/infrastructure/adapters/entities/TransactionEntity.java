package com.example.demofinantial.infrastructure.adapters.entities;


import com.example.demofinantial.domain.Status;
import com.example.demofinantial.domain.Transaction;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private Integer userId;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Double amount;
    private String message;
    private Integer walletTransactionId;
    private UUID paymentTransactionId;
    private Double fee;
    @Column(name="created_at",updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    public TransactionEntity() {
    }

    public TransactionEntity(Transaction transaction) {
        this.amount = transaction.getAmount();
        this.createdAt = transaction.getCreatedAt();
        this.paymentTransactionId = transaction.getPaymentTransactionId();
        this.walletTransactionId = transaction.getWalletTransactionId();
        this.message = transaction.getMessage();
        this.status = transaction.getStatus();
        this.userId = transaction.getUserId();
        this.fee = transaction.getFee();
    }

    public Transaction toTransaction() {
        return new Transaction(this.id, this.userId, this.status, this.message, this.paymentTransactionId, this.walletTransactionId, this.amount, this.fee, this.createdAt);
    }
}
