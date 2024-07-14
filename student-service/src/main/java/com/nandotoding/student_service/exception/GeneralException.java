package com.nandotoding.student_service.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@Data
public class GeneralException extends RuntimeException {
    private int code = 500;
    private String status = "GENERAL EXCEPTION";

    public GeneralException() {
        super("error");
    }

    public  GeneralException(String message) {
        super(message);
    }
}
