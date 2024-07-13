package com.nandotoding.authorization_service.model.request;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String username;
    private String password;
}
