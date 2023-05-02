package com.example.demofinantial.infrastructure.adapters.repositories;

import com.example.demofinantial.domain.Transaction;
import com.example.demofinantial.domain.dto.FilterParams;
import com.example.demofinantial.domain.ports.repositories.TransactionRepositoryPort;
import com.example.demofinantial.infrastructure.adapters.entities.TransactionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class TransactionRepository implements TransactionRepositoryPort {

    private final SpringPageableTransactionRepository springPageableTransactionRepository;

    private final SpringTransactionRepository springTransactionRepository;

    public TransactionRepository(SpringPageableTransactionRepository springPageableTransactionRepository, SpringTransactionRepository springTransactionRepository) {
        this.springPageableTransactionRepository = springPageableTransactionRepository;
        this.springTransactionRepository = springTransactionRepository;
    }

    @Override
    public Page<Transaction> findByFilter(FilterParams filterParams) {
        Pageable pageRequest = PageRequest.of(filterParams.getPage(), filterParams.getSize());
        Page<TransactionEntity> transactionEntities;

        if (filterParams.getAmount() != null && filterParams.getDate() == null) {
            transactionEntities = springPageableTransactionRepository.findByAmountOrderByCreatedAtDesc(filterParams.getAmount(), pageRequest);
        } else if (filterParams.getAmount() == null && filterParams.getDate() != null) {
            transactionEntities = springPageableTransactionRepository.findByCreatedAtOrderByCreatedAtDesc(filterParams.getDate(), pageRequest);
        } else {
            transactionEntities = springPageableTransactionRepository.findByAmountAndCreatedAtOrderByCreatedAtDesc(
                    filterParams.getAmount(), filterParams.getDate(), pageRequest);
        }
        return transactionEntities.map(TransactionEntity::toTransaction);
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = new TransactionEntity(transaction);
        return springTransactionRepository.save(entity).toTransaction();
    }
}
