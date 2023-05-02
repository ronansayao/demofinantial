package com.example.demofinantial.infrastructure.adapters.repositories;

import com.example.demofinantial.infrastructure.adapters.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringAccountRepository extends JpaRepository<AccountEntity, UUID> {
    Optional<AccountEntity> findByUserId(Integer userId);
}
