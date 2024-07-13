package com.nandotoding.authorization_service.controller;

import com.nandotoding.authorization_service.model.request.TokenValidationRequest;
import com.nandotoding.authorization_service.model.request.UserLoginRequest;
import com.nandotoding.authorization_service.model.request.UserLogoutRequest;
import com.nandotoding.authorization_service.model.request.UserRegisterRequest;
import com.nandotoding.authorization_service.model.response.SuccessResponse;
import com.nandotoding.authorization_service.model.response.TokenValidationResponse;
import com.nandotoding.authorization_service.model.response.UserLoginResponse;
import com.nandotoding.authorization_service.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    private final AuthorizationService authorizationService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @PostMapping("/registration")
    public ResponseEntity userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        authorizationService.registerUser(userRegisterRequest);
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), "success", null);
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        UserLoginResponse userLoginResponse = authorizationService.loginUser(userLoginRequest);
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), "success", userLoginResponse);
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity userLogout(@RequestBody UserLogoutRequest userLogoutRequest) {
        authorizationService.logoutUser(userLogoutRequest);
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), "success", null);
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }

    @PostMapping("/token-validation")
    public ResponseEntity validateToken(@RequestBody TokenValidationRequest tokenValidationRequest) {
        TokenValidationResponse tokenValidationResponse = authorizationService.validateToken(tokenValidationRequest);
        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK.value(), HttpStatus.OK.name(), "success", tokenValidationResponse);
        return ResponseEntity.status(HttpStatus.OK).body(successResponse);
    }
}
