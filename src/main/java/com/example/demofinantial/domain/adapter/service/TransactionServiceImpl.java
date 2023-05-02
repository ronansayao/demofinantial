package com.example.demofinantial.domain.adapter.service;

import com.example.demofinantial.application.exception.*;
import com.example.demofinantial.domain.Account;
import com.example.demofinantial.domain.Transaction;
import com.example.demofinantial.domain.dto.FilterParams;
import com.example.demofinantial.domain.dto.TransactionDTO;
import com.example.demofinantial.domain.ports.interfaces.TransactionServicePort;
import com.example.demofinantial.domain.ports.repositories.*;
import org.springframework.data.domain.Page;

import java.util.Optional;

public class TransactionServiceImpl implements TransactionServicePort {

    private final AccountRepositoryPort accountRepository;

    private final BalanceRepositoryPort balanceRepository;

    private final PaymentRepositoryPort paymentRepositoryPort;

    private final WalletRepositoryPort walletRepositoryPort;

    private final TransactionRepositoryPort transactionRepositoryPort;

    public TransactionServiceImpl(AccountRepositoryPort accountRepository, BalanceRepositoryPort balanceRepository, PaymentRepositoryPort paymentRepository, WalletRepositoryPort walletRepository, TransactionRepositoryPort transactionRepositoryPort) {
        this.accountRepository = accountRepository;
        this.balanceRepository = balanceRepository;
        this.paymentRepositoryPort = paymentRepository;
        this.walletRepositoryPort = walletRepository;
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Override
    public Transaction transfer(TransactionDTO transferDTO) throws AccountNotFoundException, InsufficientFundsException, MalformedRequestException, UserNotFoundException, UnknownException {
        Double fee = transferDTO.getAmount()*0.1;
        //check if account exists
        Optional<Account> destinationAccount = accountRepository.findByUserId(transferDTO.getUserId());
        if (!destinationAccount.isPresent()) {
            throw new AccountNotFoundException();
        }

        //check if account has sufficient funds
        if (balanceRepository.getBalance(transferDTO.getUserId()).getBalance().compareTo(transferDTO.getAmount() + fee) < 0) {
            throw new InsufficientFundsException();
        }

        //call the api that make the payment, and check the status
        Transaction transaction = paymentRepositoryPort.transfer(destinationAccount.get(), transferDTO.getAmount());

        //call the wallet transaction
        Integer walletTransactionId = walletRepositoryPort.transfer(transferDTO.getUserId(), transferDTO.getAmount() + fee);
        transaction.setFee(fee);
        transaction.setWalletTransactionId(walletTransactionId);
        transaction.setUserId(transferDTO.getUserId());

        //create internal transaction
        transaction = transactionRepositoryPort.save(transaction);

        return transaction;
    }

    @Override
    public Page<Transaction> searchTransactionsByFilter(FilterParams filterParams) {
        return transactionRepositoryPort.findByFilter(filterParams);
    }

}
