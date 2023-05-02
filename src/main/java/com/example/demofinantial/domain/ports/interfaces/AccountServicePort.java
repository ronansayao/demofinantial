package com.example.demofinantial.domain.ports.interfaces;

import com.example.demofinantial.domain.Account;
import com.example.demofinantial.domain.dto.AccountDTO;

public interface AccountServicePort {
    Account createAccount(Integer userId, AccountDTO accountDTO);
}
