package com.example.demofinantial.application.controller;


import com.example.demofinantial.domain.Account;
import com.example.demofinantial.domain.dto.AccountDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountControllerTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createNewAccount() {

        String createAccountUrl = "http://localhost:" + port + "/account/1000";
        Account account = restTemplate.postForObject(createAccountUrl, getAccountDTO(), Account.class);
        assertThat(account.getFirstName()).isEqualTo("Tony");
        assertThat(account.getLastName()).isEqualTo("Stark");


    }

    private AccountDTO getAccountDTO() {
        return AccountDTO.builder().firstName("Tony").lastName("Stark")
                .routingNumber("623852453")
                .nationalIdentificationNumber("33278129")
                .accountNumber("23100299907")
                .userId(1000).build();
    }

}
