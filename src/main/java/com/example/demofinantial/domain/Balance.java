package com.example.demofinantial.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Balance {
    private Double balance;
    @JsonProperty("user_id")
    private Integer userId;
}
