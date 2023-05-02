package com.example.demofinantial.domain.ports.repositories;

import com.example.demofinantial.application.exception.MalformedRequestException;
import com.example.demofinantial.domain.Account;
import com.example.demofinantial.domain.Transaction;

public interface PaymentRepositoryPort {
    Transaction transfer(Account destinationAccount, Double amount) throws MalformedRequestException;
}
