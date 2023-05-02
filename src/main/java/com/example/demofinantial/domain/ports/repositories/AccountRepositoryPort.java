package com.example.demofinantial.domain.ports.repositories;

import com.example.demofinantial.domain.Account;

import java.util.Optional;

public interface AccountRepositoryPort {
    Account createAccount(Account account, Integer userId);
    Optional<Account> findByUserId(Integer userId);
}
