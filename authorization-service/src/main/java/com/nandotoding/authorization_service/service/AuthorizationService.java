package com.nandotoding.authorization_service.service;

import com.nandotoding.authorization_service.model.request.TokenValidationRequest;
import com.nandotoding.authorization_service.model.request.UserLoginRequest;
import com.nandotoding.authorization_service.model.request.UserLogoutRequest;
import com.nandotoding.authorization_service.model.request.UserRegisterRequest;
import com.nandotoding.authorization_service.model.response.TokenValidationResponse;
import com.nandotoding.authorization_service.model.response.UserLoginResponse;

public interface AuthorizationService {
    public void registerUser(UserRegisterRequest userRegisterRequest);
    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest);
    public void logoutUser(UserLogoutRequest userLogoutRequest);
    public TokenValidationResponse validateToken(TokenValidationRequest tokenValidationRequest);

}
