package com.example.demofinantial.application.controller;

import com.example.demofinantial.domain.Account;
import com.example.demofinantial.domain.dto.AccountDTO;
import com.example.demofinantial.domain.ports.interfaces.AccountServicePort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountServicePort accountServicePort;

    public AccountController(AccountServicePort accountServicePort) {
        this.accountServicePort = accountServicePort;
    }

    @PostMapping(value = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    Account createAccount(@PathVariable Integer userId, @RequestBody AccountDTO accountDTO) {
        return accountServicePort.createAccount(userId, accountDTO);
    }
}
