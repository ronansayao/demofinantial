package com.example.demofinantial.application.controller;

import com.example.demofinantial.application.exception.*;
import com.example.demofinantial.domain.Transaction;
import com.example.demofinantial.domain.dto.FilterParams;
import com.example.demofinantial.domain.dto.TransactionDTO;
import com.example.demofinantial.domain.ports.interfaces.TransactionServicePort;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionServicePort transactionServicePort;

    public TransactionController(TransactionServicePort transactionServicePort) {
        this.transactionServicePort = transactionServicePort;
    }

    @PostMapping
    Transaction createTransaction(@RequestBody TransactionDTO transferDTO) {
        try {

            return transactionServicePort.transfer(transferDTO);

        } catch (AccountNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Account not found.", e);
        } catch (InsufficientFundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient funds for this transaction.", e);
        } catch (MalformedRequestException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "This error occurred when called when call external API, check source and destination account structure.", e);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found.", e);
        } catch (UnknownException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
    }

    @GetMapping
    Page<Transaction> listTransactionByFilter(@RequestPart("filterParams") FilterParams filterParams) {
        return transactionServicePort.searchTransactionsByFilter(filterParams);
    }
}
