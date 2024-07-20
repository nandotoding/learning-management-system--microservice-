package com.nandotoding.student_service.external.service;

import com.nandotoding.student_service.exception.GeneralException;
import com.nandotoding.student_service.exception.UnauthorizedException;
import com.nandotoding.student_service.external.model.TokenValidationResponse;
import com.nandotoding.student_service.model.request.TokenValidationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthorizationService {
    private final WebClient.Builder webClientBuilder;

    @Autowired
    public AuthorizationService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
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

        if (!response.getData().isValid()) {
            throw new UnauthorizedException("token not valid");
        } else {
            return true;
        }
    }
}
