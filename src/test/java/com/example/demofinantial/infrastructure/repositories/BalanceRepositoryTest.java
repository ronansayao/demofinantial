package com.example.demofinantial.infrastructure.repositories;

import com.example.demofinantial.domain.Balance;
import com.example.demofinantial.domain.ports.repositories.BalanceRepositoryPort;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class BalanceRepositoryTest {

    @Autowired
    private BalanceRepositoryPort balanceRepositoryPort;

    @Test
    public void getBalance() {
        Balance balance = balanceRepositoryPort.getBalance(1000);
        assertThat(balance.getBalance()).isEqualTo(2500);
    }


}
