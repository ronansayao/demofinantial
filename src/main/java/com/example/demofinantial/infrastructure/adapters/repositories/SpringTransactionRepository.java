package com.example.demofinantial.infrastructure.adapters.repositories;

import com.example.demofinantial.infrastructure.adapters.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringTransactionRepository extends JpaRepository<TransactionEntity, UUID>  {

}
