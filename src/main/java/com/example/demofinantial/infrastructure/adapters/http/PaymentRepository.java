package com.example.demofinantial.infrastructure.adapters.http;

import com.example.demofinantial.application.exception.MalformedRequestException;
import com.example.demofinantial.domain.Account;
import com.example.demofinantial.domain.Status;
import com.example.demofinantial.domain.Transaction;
import com.example.demofinantial.domain.externalDto.*;
import com.example.demofinantial.domain.ports.repositories.PaymentRepositoryPort;
import com.example.demofinantial.infrastructure.configurations.SourceAccountProperties;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class PaymentRepository implements PaymentRepositoryPort {

    private static final String CURRENCY = "USD";
    public static final String CONTENT_TYPE = "Content-Type";

    @Value("${external.payment.url}")
    private String url;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private SourceAccountProperties properties;

    @Override
    public Transaction transfer(Account destinationAccount, Double amount) throws MalformedRequestException {

        try {
            PaymentTransactionRequest transaction = init(destinationAccount, amount);
            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .headers(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(transaction)))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 400) {
                throw new MalformedRequestException();
            } else {
                PaymentResponse paymentResponse = objectMapper.readValue(response.body(), PaymentResponse.class);
                return Transaction.builder()
                        .status(getStatus(paymentResponse.getRequestInfo().getStatus()))
                        .paymentTransactionId(paymentResponse.getPaymentInfo().getId())
                        .message(paymentResponse.getRequestInfo().getError())
                        .amount(amount).build();
            }

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static Status getStatus(String externalStatus) {

        return switch(externalStatus.toLowerCase()) {
            case "processing":
                yield Status.IN_PROGRESS;
            case "failed", "timeout":
                yield Status.FAILED;
            default:
                yield Status.COMPLETED;
        };
    }

    private PaymentTransactionRequest init(Account destinationAccount, Double amount) {
        SourceAccount externalSourceAccount = SourceAccount.builder().type(properties.getType())
                .sourceInformation(SourceInformation.builder().name(properties.getName()).build())
                .account(CommonAccount.builder().accountNumber(properties.getAccountNumber())
                .currency(CURRENCY).routingNumber(properties.getRoutingNumber()).build()).build();

        DestinationAccount externalDestinationAccount = DestinationAccount.builder().name(destinationAccount.getFirstName()+" "+ destinationAccount.getLastName())
                .account(CommonAccount.builder().accountNumber(destinationAccount.getAccountNumber())
                        .currency(CURRENCY)
                        .routingNumber(destinationAccount.getRoutingNumber()).build()).build();

        return PaymentTransactionRequest.builder().source(externalSourceAccount)
                .destination(externalDestinationAccount)
                .amount(amount).build();
    }
}
