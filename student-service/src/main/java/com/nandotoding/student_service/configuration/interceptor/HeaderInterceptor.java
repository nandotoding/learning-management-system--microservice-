package com.nandotoding.student_service.configuration.interceptor;

import com.nandotoding.student_service.external.service.AuthorizationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class HeaderInterceptor implements HandlerInterceptor {
    private final AuthorizationService authorizationService;

    @Autowired
    public HeaderInterceptor(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().contains("/auth")) {
            return true;
        }

        String token = request.getHeader("Authorization").replace("Bearer ", "");
        return authorizationService.validateToken(token);
    }
}
