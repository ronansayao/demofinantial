package com.example.demofinantial.application.controller;

import com.example.demofinantial.domain.Status;
import com.example.demofinantial.domain.Transaction;
import com.example.demofinantial.domain.dto.FilterParams;
import com.example.demofinantial.domain.dto.TransactionDTO;
import com.example.demofinantial.infrastructure.utils.PaginatedResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransactionControllerTest {
    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void createNewTransaction() {
        String createTransactionUrl = "http://localhost:" + port + "/transaction";
        Transaction response = restTemplate.postForObject(createTransactionUrl, getTransactionDTO(), Transaction.class);
        assertThat(response).isNotNull();
        assertThat(response.getStatus()).isEqualTo(Status.IN_PROGRESS);
        assertThat(response.getId()).isNotNull();
        assertThat(response.getAmount()).isEqualTo(2000);
        assertThat(response.getFee()).isEqualTo(200);
    }

    @Test
    public void createTransactionWithNoBalance() {
        String createTransactionUrl = "http://localhost:" + port + "/transaction";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<TransactionDTO> requestEntity = new HttpEntity<>(getTransactionDTONoBalance(), headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(createTransactionUrl,
                requestEntity, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(400));
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void searchTransaction() {
        String createTransactionUrl = "http://localhost:" + port + "/transaction";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("filterParams", getFilterParams());

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        ParameterizedTypeReference<PaginatedResponse<Transaction>> responseType = new ParameterizedTypeReference<>() {
        };

        Page<Transaction> transactions = restTemplate.exchange(URI.create(createTransactionUrl),
                HttpMethod.GET, requestEntity, responseType).getBody();

        assertThat(transactions.getContent().size()).isEqualTo(2);

    }

    private TransactionDTO getTransactionDTO() {
        return TransactionDTO.builder().userId(1000).amount(2000.00).build();
    }

    private TransactionDTO getTransactionDTONoBalance() {
        return TransactionDTO.builder().userId(1000).amount(20000.00).build();
    }

    private FilterParams getFilterParams() {
        return FilterParams.builder()
                .amount((double) -100)
                .page(0)
                .size(5).build();
    }

}
