package com.nandotoding.authorization_service.util;

import com.nandotoding.authorization_service.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Data
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExp;
    private Map<String, String> tokenStorage = new HashMap<>();

    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .setExpiration(new Date(System.currentTimeMillis() + jwtExp))
                .compact();
    }

    public boolean validateToken(String token) {
        if (token == null) {
            throw new UnauthorizedException();
        }

        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new UnauthorizedException("Token expired");
        } catch (UnsupportedJwtException e) {
            throw new UnauthorizedException("Token unsupported");
        } catch (MalformedJwtException e) {
            throw new UnauthorizedException("Token malformed");
        } catch (SignatureException e) {
            throw new UnauthorizedException("Signature unknown");
        }
    }

    public void addTokenToStorage(String username, String token) {
        this.tokenStorage.put(username, token);
    }

    public boolean removeTokenFromStorage(String username) {
        String removedToken = this.tokenStorage.remove(username);
        return removedToken != null;
    }

    public boolean isTokenRevoked(String username) {
        return tokenStorage.containsKey(username);
    }
}
