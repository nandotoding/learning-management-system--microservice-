package com.nandotoding.student_service.external.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nandotoding.student_service.exception.GeneralException;
import com.nandotoding.student_service.model.request.TokenValidationRequest;
import com.nandotoding.student_service.model.response.TokenValidationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthorizationService {
    private final WebClient.Builder webClientBuilder;
    private final ObjectMapper objectMapper;

    @Autowired
    public AuthorizationService(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webClientBuilder = webClientBuilder;
        this.objectMapper = objectMapper;
    }

    public boolean validateToken(String token) {
        String validateTokenUrl = "http://localhost:8081/auth/token-validation";
        TokenValidationRequest request = new TokenValidationRequest(token);
        StringBuilder stringBuilder = new StringBuilder();

        TokenValidationResponse response = webClientBuilder.build()
                        .post()
                        .uri(validateTokenUrl)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(request))
                        .retrieve()
                        .bodyToMono(TokenValidationResponse.class)
                        .block();

        if (response == null) {
            throw new GeneralException("failed to fetched token validation response data");
        }

        return response.isValid();
    }
}
