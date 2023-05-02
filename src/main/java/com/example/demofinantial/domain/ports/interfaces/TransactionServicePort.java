package com.example.demofinantial.domain.ports.interfaces;

import com.example.demofinantial.application.exception.*;
import com.example.demofinantial.domain.Transaction;
import com.example.demofinantial.domain.dto.FilterParams;
import com.example.demofinantial.domain.dto.TransactionDTO;
import org.springframework.data.domain.Page;

public interface TransactionServicePort {
    Transaction transfer(TransactionDTO transferDTO) throws AccountNotFoundException, InsufficientFundsException, MalformedRequestException, UserNotFoundException, UnknownException;

    Page<Transaction> searchTransactionsByFilter(FilterParams filterParams);
}
