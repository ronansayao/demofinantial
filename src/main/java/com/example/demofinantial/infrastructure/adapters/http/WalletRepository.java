package com.example.demofinantial.infrastructure.adapters.http;

import com.example.demofinantial.application.exception.MalformedRequestException;
import com.example.demofinantial.application.exception.UnknownException;
import com.example.demofinantial.application.exception.UserNotFoundException;
import com.example.demofinantial.domain.externalDto.WalletError;
import com.example.demofinantial.domain.externalDto.WalletTransactionRequest;
import com.example.demofinantial.domain.externalDto.WalletTransactionResponse;
import com.example.demofinantial.domain.ports.repositories.WalletRepositoryPort;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class WalletRepository implements WalletRepositoryPort {

    public static final String CONTENT_TYPE = "Content-Type";

    @Value("${external.wallet.url}")
    private String url;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Integer transfer(Integer userId, Double amount) throws MalformedRequestException, UserNotFoundException, UnknownException {
        try {
            WalletTransactionRequest walletTransactionRequest = WalletTransactionRequest.builder()
                    .userId(userId)
                    .amount(amount).build();

            objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .headers(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(walletTransactionRequest)))
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 400) {
                throw new MalformedRequestException();
            } else if (response.statusCode() == 404) {
                throw new UserNotFoundException();
            } else if (response.statusCode() == 200) {
                WalletTransactionResponse walletTransactionResponse = objectMapper.readValue(response.body(), WalletTransactionResponse.class);
                return walletTransactionResponse.getWalletTransactionId();
            } else {
                WalletError walletError = objectMapper.readValue(response.body(), WalletError.class);
                throw new UnknownException(walletError.getCode()+" - "+walletError.getMessage());
            }

        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }


}
