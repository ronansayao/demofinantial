package com.example.demofinantial.infrastructure.adapters.repositories;


import com.example.demofinantial.domain.Account;
import com.example.demofinantial.domain.ports.repositories.AccountRepositoryPort;
import com.example.demofinantial.infrastructure.adapters.entities.AccountEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountRepository implements AccountRepositoryPort {

    private final SpringAccountRepository springAccountRepository;

    public AccountRepository(SpringAccountRepository springAccountRepository) {
        this.springAccountRepository = springAccountRepository;
    }

    @Override
    public Account createAccount(Account account, Integer userId) {
        Optional<AccountEntity> accountEntity = springAccountRepository.findByUserId(userId);
        if (accountEntity.isPresent()) {
            return accountEntity.get().toAccount();
        } else {
            return springAccountRepository.save(new AccountEntity(account, userId)).toAccount();
        }
    }

    @Override
    public Optional<Account> findByUserId(Integer userId) {
        Optional<AccountEntity> accountEntity = springAccountRepository.findByUserId(userId);
        return accountEntity.map(AccountEntity::toAccount);
    }
}
