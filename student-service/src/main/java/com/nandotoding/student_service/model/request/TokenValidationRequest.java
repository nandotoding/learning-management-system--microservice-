package com.nandotoding.student_service.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenValidationRequest {
    private String token;
}
