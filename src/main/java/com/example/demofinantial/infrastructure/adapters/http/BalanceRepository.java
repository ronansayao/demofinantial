package com.example.demofinantial.infrastructure.adapters.http;

import com.example.demofinantial.domain.Balance;
import com.example.demofinantial.domain.ports.repositories.BalanceRepositoryPort;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class BalanceRepository implements BalanceRepositoryPort {

    @Value("${external.balance.url}")
    private String url;

    @Override
    public Balance getBalance(Integer userId) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url+userId))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response.body(), Balance.class);
        } catch (URISyntaxException | IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
