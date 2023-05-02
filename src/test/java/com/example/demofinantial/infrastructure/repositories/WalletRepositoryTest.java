package com.example.demofinantial.infrastructure.repositories;

import com.example.demofinantial.application.exception.MalformedRequestException;
import com.example.demofinantial.application.exception.UnknownException;
import com.example.demofinantial.application.exception.UserNotFoundException;
import com.example.demofinantial.domain.ports.repositories.WalletRepositoryPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class WalletRepositoryTest {
    @Autowired
    private WalletRepositoryPort walletRepositoryPort;

    @Test
    public void topUpWalletTransaction() throws UserNotFoundException, MalformedRequestException, UnknownException {
        Integer walletTransactionId = walletRepositoryPort.transfer(1000, 2000.0);
        assertThat(walletTransactionId).isNotNull();
    }

    @Test
    public void withdrawWalletTransaction() throws UserNotFoundException, MalformedRequestException, UnknownException {
        Integer walletTransactionId = walletRepositoryPort.transfer(1000, -2300.0);
        assertThat(walletTransactionId).isNotNull();
    }

    @Test
    public void malformedWalletTransaction() {
        MalformedRequestException thrown = Assertions.assertThrows(MalformedRequestException.class, () -> {
            Integer walletTransactionId = walletRepositoryPort.transfer(null, 2300.0);
        }, "MalformedRequestException was expected");
        assertThat(thrown.getMessage()).isNull();
    }

    @Test
    public void notFoundWalletTransaction() {
        UserNotFoundException thrown = Assertions.assertThrows(UserNotFoundException.class, () -> {
            Integer walletTransactionId = walletRepositoryPort.transfer(404, 2000.0);
        }, "UserNotFoundException was expected");
        assertThat(thrown.getMessage()).isNull();
    }

    @Test
    public void genericErrorWalletTransaction() {
        UnknownException thrown = Assertions.assertThrows(UnknownException.class, () -> {
            Integer walletTransactionId = walletRepositoryPort.transfer(500, 2000.0);
        }, "UnknownException was expected");
        assertThat(thrown.getMessage()).isEqualTo("GENERIC_ERROR - something bad happened");
    }
}
