package com.nandotoding.authorization_service.model.request;

import lombok.Data;

@Data
public class TokenValidationRequest {
    private String token;
}
