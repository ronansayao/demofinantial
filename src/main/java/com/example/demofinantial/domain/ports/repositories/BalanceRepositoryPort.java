package com.example.demofinantial.domain.ports.repositories;

import com.example.demofinantial.domain.Balance;

public interface BalanceRepositoryPort {
    Balance getBalance(Integer userId);
}
