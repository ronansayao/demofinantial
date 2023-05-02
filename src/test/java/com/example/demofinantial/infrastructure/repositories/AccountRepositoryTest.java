package com.example.demofinantial.infrastructure.repositories;

import com.example.demofinantial.domain.Account;
import com.example.demofinantial.infrastructure.adapters.entities.AccountEntity;
import com.example.demofinantial.infrastructure.adapters.repositories.SpringAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    private SpringAccountRepository springAccountRepository;

    @Test
    public void createAccount() {
        AccountEntity accountEntity = new AccountEntity(getAccount(), 1000);
        AccountEntity savedAccount = springAccountRepository.save(accountEntity);
        Optional<AccountEntity> accountEntityFound = springAccountRepository.findById(savedAccount.getId());
        assertThat(accountEntityFound.isPresent()).isTrue();
        assertThat(accountEntityFound.get().getId()).isEqualTo(savedAccount.getId());
        assertThat(accountEntityFound.get().getUserId()).isEqualTo(savedAccount.getUserId());
        assertThat(accountEntityFound.get().getFirstName()).isEqualTo(savedAccount.getFirstName());
    }

    private Account getAccount() {
        return new Account(1000, "John", "Smith", "623852453", "33278129", "23100299907");
    }


}
