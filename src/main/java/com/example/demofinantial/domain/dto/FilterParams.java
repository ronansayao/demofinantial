package com.example.demofinantial.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class FilterParams {
    private Double amount;
    private LocalDateTime date;
    private int page;
    private int size;
}
