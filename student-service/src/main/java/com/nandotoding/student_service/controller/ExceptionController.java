package com.nandotoding.student_service.controller;

import com.nandotoding.student_service.exception.GeneralException;
import com.nandotoding.student_service.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity handleGeneralException(GeneralException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getCode(), e.getStatus(), e.getMessage()));
    }
}
