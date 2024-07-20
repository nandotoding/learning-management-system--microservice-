package com.nandotoding.student_service.external.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenValidationResponse {
    private int code;
    private String status;
    private String message;
    private TokenValidationResponseData data;

    @Data
    public static class TokenValidationResponseData {
        private boolean valid;
    }
}
