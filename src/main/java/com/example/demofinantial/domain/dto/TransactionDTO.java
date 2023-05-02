package com.example.demofinantial.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;


@Builder
@Getter
public class TransactionDTO implements Serializable {
    private Integer userId;
    private Double amount;
}
