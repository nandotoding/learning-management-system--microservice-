package com.nandotoding.authorization_service.service.implementation;

import com.nandotoding.authorization_service.exception.BadRequestException;
import com.nandotoding.authorization_service.model.entity.Credential;
import com.nandotoding.authorization_service.model.request.TokenValidationRequest;
import com.nandotoding.authorization_service.model.request.UserLoginRequest;
import com.nandotoding.authorization_service.model.request.UserLogoutRequest;
import com.nandotoding.authorization_service.model.request.UserRegisterRequest;
import com.nandotoding.authorization_service.model.response.TokenValidationResponse;
import com.nandotoding.authorization_service.model.response.UserLoginResponse;
import com.nandotoding.authorization_service.repository.CredentialRepository;
import com.nandotoding.authorization_service.service.AuthorizationService;
import com.nandotoding.authorization_service.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationServiceImplementation implements AuthorizationService {
    private final CredentialRepository credentialRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthorizationServiceImplementation(CredentialRepository credentialRepository, JwtUtil jwtUtil) {
        this.credentialRepository = credentialRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void registerUser(UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BadRequestException("empty request data");
        }

        if (credentialRepository.getCredentialByUsername(userRegisterRequest.getUsername()) != null) {
            throw new BadRequestException("user already exists");
        }

        final boolean isAdmin = false;
        final boolean isDeleted = false;
        final String id = UUID.randomUUID().toString();

        credentialRepository.addCredential(id, userRegisterRequest.getUsername(), userRegisterRequest.getPassword(), isAdmin, isDeleted);
    }

    @Override
    public UserLoginResponse loginUser(UserLoginRequest userLoginRequest) {
        if (userLoginRequest == null) {
            throw new BadRequestException("empty request data");
        }

        Credential credential = credentialRepository.getCredentialByUsername(userLoginRequest.getUsername());

        if (credential == null) {
            throw new BadRequestException("incorrect username or password");
        }

        if (!userLoginRequest.getPassword().equals(credential.getPassword())) {
            throw new BadRequestException("incorrect username or password");
        }

        String token = jwtUtil.generateToken(credential.getUsername());
        jwtUtil.addSession(credential.getUsername());
        return new UserLoginResponse(credential.getUsername(), token);
    }

    @Override
    public void logoutUser(UserLogoutRequest userLogoutRequest) {
        jwtUtil.removeSession(userLogoutRequest.getUsername());
    }

    @Override
    public TokenValidationResponse validateToken(TokenValidationRequest tokenValidationRequest) {
        boolean isTokenValid = jwtUtil.validateToken(tokenValidationRequest.getToken());
        String username = jwtUtil.extractUsername(tokenValidationRequest.getToken());
        boolean isActiveSession = jwtUtil.isActiveSession(username);
        return new TokenValidationResponse(isTokenValid && isActiveSession);
    }
}
