package com.example.demofinantial.infrastructure.adapters.repositories;

import com.example.demofinantial.infrastructure.adapters.entities.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface SpringPageableTransactionRepository extends PagingAndSortingRepository<TransactionEntity, UUID> {

    Page<TransactionEntity> findByAmountOrderByCreatedAtDesc(Double amount, Pageable pageable);

    Page<TransactionEntity> findByCreatedAtOrderByCreatedAtDesc(LocalDateTime createdAt, Pageable pageable);

    Page<TransactionEntity> findByAmountAndCreatedAtOrderByCreatedAtDesc(Double amount, LocalDateTime createdAt, Pageable pageable);


}
