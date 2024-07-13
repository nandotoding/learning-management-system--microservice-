package com.nandotoding.authorization_service.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@Data
public class UnauthorizedException extends RuntimeException {
    private int code = 401;
    private String status = "UNAUTHORIZED";

    public UnauthorizedException() {
        super("unauthorized");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
