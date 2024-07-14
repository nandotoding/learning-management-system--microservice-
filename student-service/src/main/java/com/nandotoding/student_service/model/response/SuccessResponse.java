package com.nandotoding.student_service.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessResponse<T> {
    private int code;
    private String status;
    private String message;
    private T data;
}
