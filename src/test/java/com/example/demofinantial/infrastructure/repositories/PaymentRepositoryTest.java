package com.example.demofinantial.infrastructure.repositories;

import com.example.demofinantial.application.exception.MalformedRequestException;
import com.example.demofinantial.domain.Account;
import com.example.demofinantial.domain.Status;
import com.example.demofinantial.domain.Transaction;
import com.example.demofinantial.domain.ports.repositories.PaymentRepositoryPort;
import com.example.demofinantial.infrastructure.configurations.SourceAccountProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class PaymentRepositoryTest {

    @Autowired
    private PaymentRepositoryPort paymentRepositoryPort;

    @Autowired
    private SourceAccountProperties properties;

    @Test
    public void createTransaction() throws MalformedRequestException {
        Transaction transaction = paymentRepositoryPort.transfer(getDestinationAccount(), 1000.0);
        assertThat(transaction.getPaymentTransactionId()).isNotNull();
        assertThat(transaction.getStatus()).isEqualTo(Status.IN_PROGRESS);
    }

    @Test
    public void malformedTransaction() {

        ReflectionTestUtils.setField(properties, "type", "COMPANY TWO");
        MalformedRequestException thrown = Assertions.assertThrows(MalformedRequestException.class, () -> {
            Transaction transaction = paymentRepositoryPort.transfer(getDestinationAccount(), 1000.0);
        }, "MalformedRequestException was expected");
        System.out.println(thrown.getMessage());
        assertThat(thrown.getMessage()).isNull();
    }

    @Test
    public void failedTransaction() throws MalformedRequestException {
        Transaction transaction = paymentRepositoryPort.transfer(getDestinationAccountFailed("FAILED"), 1000.0);
        assertThat(transaction.getStatus()).isEqualTo(Status.FAILED);
        assertThat(transaction.getMessage()).isEqualTo("bank rejected payment");
    }

    @Test
    public void timeoutTransaction() throws MalformedRequestException {
        Transaction transaction = paymentRepositoryPort.transfer(getDestinationAccountFailed("TIMEOUT"), 1000.0);
        assertThat(transaction.getStatus()).isEqualTo(Status.FAILED);
        assertThat(transaction.getMessage()).isEqualTo("timeout");
    }
    private Account getDestinationAccount() {
        return Account.builder()
                .userId(1000)
                .firstName("TONY")
                .lastName("STARK")
                .accountNumber("1885226711")
                .routingNumber("211927207")
                .nationalIdentificationNumber("123456789")
                .build();
    }

    private Account getDestinationAccountFailed(String type) {
        return Account.builder()
                .userId(1000)
                .firstName("JAMES")
                .lastName(type)
                .accountNumber("1885226711")
                .routingNumber("211927207")
                .nationalIdentificationNumber("123456789")
                .build();
    }


}
