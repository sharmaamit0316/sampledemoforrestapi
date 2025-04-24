package com.example.demo.client;

import com.example.demo.exception.ExternalServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

@Component
public class ExternalApiClient {

    private final WebClient.Builder webClientBuilder;

    public ExternalApiClient(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public ExternalResponse getExternalTodoDetails(Long id) {
        try {
            // Call external service
            return webClientBuilder.baseUrl("https://external-service.com/api")
                    .build()
                    .get()
                    .uri("/todos/{id}", id)
                    .retrieve()
                    .onStatus(HttpStatus::is4xxClientError, clientResponse ->
                            Mono.error(new ExternalServiceException("Client error: " + clientResponse.statusCode())))
                    .onStatus(HttpStatus::is5xxServerError, clientResponse ->
                            Mono.error(new ExternalServiceException("Server error: " + clientResponse.statusCode())))
                    .bodyToMono(ExternalResponse.class)
                    .block(); // Synchronous call
        } catch (Exception e) {
            // Handle any other exceptions during the external service call
            throw new ExternalServiceException("Failed to fetch external data", e);
        }
    }
}
