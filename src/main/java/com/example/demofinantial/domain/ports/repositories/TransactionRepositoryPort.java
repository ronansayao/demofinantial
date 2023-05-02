package com.example.demofinantial.domain.ports.repositories;

import com.example.demofinantial.domain.Transaction;
import com.example.demofinantial.domain.dto.FilterParams;
import org.springframework.data.domain.Page;

public interface TransactionRepositoryPort {
    Page<Transaction> findByFilter(FilterParams filterParams);

    Transaction save(Transaction transaction);
}
