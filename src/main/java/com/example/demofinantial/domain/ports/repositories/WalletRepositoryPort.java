package com.example.demofinantial.domain.ports.repositories;

import com.example.demofinantial.application.exception.MalformedRequestException;
import com.example.demofinantial.application.exception.UnknownException;
import com.example.demofinantial.application.exception.UserNotFoundException;

public interface WalletRepositoryPort {
    Integer transfer(Integer userId, Double amount) throws MalformedRequestException, UserNotFoundException, UnknownException;
}
