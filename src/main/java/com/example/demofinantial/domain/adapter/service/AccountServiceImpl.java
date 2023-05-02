package com.example.demofinantial.domain.adapter.service;

import com.example.demofinantial.domain.Account;
import com.example.demofinantial.domain.dto.AccountDTO;
import com.example.demofinantial.domain.ports.interfaces.AccountServicePort;
import com.example.demofinantial.domain.ports.repositories.AccountRepositoryPort;

public class AccountServiceImpl implements AccountServicePort {

    private final AccountRepositoryPort accountRepository;

    public AccountServiceImpl(AccountRepositoryPort accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public Account createAccount(Integer userId, AccountDTO accountDTO) {
        return accountRepository.createAccount(new Account(accountDTO), userId);
    }
}
