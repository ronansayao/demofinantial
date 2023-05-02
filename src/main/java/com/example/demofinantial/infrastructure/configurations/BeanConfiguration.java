package com.example.demofinantial.infrastructure.configurations;


import com.example.demofinantial.domain.adapter.service.AccountServiceImpl;
import com.example.demofinantial.domain.adapter.service.TransactionServiceImpl;
import com.example.demofinantial.domain.ports.interfaces.AccountServicePort;
import com.example.demofinantial.domain.ports.interfaces.TransactionServicePort;
import com.example.demofinantial.domain.ports.repositories.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    AccountServicePort accountService(AccountRepositoryPort accountRepositoryPort) {
        return new AccountServiceImpl(accountRepositoryPort);
    }

    @Bean
    TransactionServicePort transferService(AccountRepositoryPort accountRepository, BalanceRepositoryPort balanceRepository,
                                           PaymentRepositoryPort paymentRepository, WalletRepositoryPort walletRepository,
                                            TransactionRepositoryPort transactionRepository) {
        return new TransactionServiceImpl(accountRepository, balanceRepository, paymentRepository, walletRepository, transactionRepository);
    }

}
