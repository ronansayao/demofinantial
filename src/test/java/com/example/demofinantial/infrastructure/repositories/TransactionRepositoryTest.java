package com.example.demofinantial.infrastructure.repositories;

import com.example.demofinantial.infrastructure.adapters.entities.TransactionEntity;
import com.example.demofinantial.infrastructure.adapters.repositories.SpringPageableTransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransactionRepositoryTest {

    @Autowired
    private SpringPageableTransactionRepository transactionRepository;

    @Test
    public void searchByAmount() {
        Pageable pageRequest = PageRequest.of(0, 10);
        var amount = Double.valueOf(-20);
        Page<TransactionEntity> transactions = transactionRepository.findByAmountOrderByCreatedAtDesc(amount, pageRequest);
        assertThat(transactions.getContent().size()).isEqualTo(2);
    }

    @Test
    public void searchByCreatedAt() {
        Pageable pageRequest = PageRequest.of(0, 10);
        LocalDateTime date = LocalDateTime.of(2023, 5, 1, 11, 1, 1 );
        Page<TransactionEntity> transactions = transactionRepository.findByCreatedAtOrderByCreatedAtDesc(date, pageRequest);
        assertThat(transactions.getContent().size()).isEqualTo(1);
    }

    @Test
    public void searchByAmountAndCreatedAt() {
        Pageable pageRequest = PageRequest.of(0, 10);
        var amount = Double.valueOf(-100);
        LocalDateTime date = LocalDateTime.of(2023, 4, 30, 16, 10, 12 );
        Page<TransactionEntity> transactions = transactionRepository.findByAmountAndCreatedAtOrderByCreatedAtDesc(amount , date, pageRequest);
        assertThat(transactions.getContent().size()).isEqualTo(1);
    }

    @Test
    public void pagination() {
        Pageable pageRequest = PageRequest.of(0, 1);
        var amount = Double.valueOf(-20);
        Page<TransactionEntity> transactions = transactionRepository.findByAmountOrderByCreatedAtDesc(amount, pageRequest);
        assertThat(transactions.getContent().size()).isEqualTo(1);
        assertThat(transactions.getTotalPages()).isEqualTo(2);
    }

    @Test
    public void paginationOnePage() {
        Pageable pageRequest = PageRequest.of(0, 2);
        var amount = Double.valueOf(-20);
        Page<TransactionEntity> transactions = transactionRepository.findByAmountOrderByCreatedAtDesc(amount, pageRequest);
        assertThat(transactions.getContent().size()).isEqualTo(2);
        assertThat(transactions.getTotalPages()).isEqualTo(1);
    }

}
