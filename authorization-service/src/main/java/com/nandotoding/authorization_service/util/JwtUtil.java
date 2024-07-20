package com.nandotoding.authorization_service.util;

import com.nandotoding.authorization_service.exception.UnauthorizedException;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Data
public class JwtUtil {
    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExp;
    private Set<String> userSession = new HashSet<>();

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .setExpiration(new Date(System.currentTimeMillis() + jwtExp))
                .compact();
    }

    public boolean validateToken(String token) {
        if (token == null) {
            throw new UnauthorizedException("Token is null");
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

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public void addSession(String username) {
        this.userSession.add(username);
    }

    public void removeSession(String username) {
        this.userSession.remove(username);
    }

    public boolean isActiveSession(String username) {
        return this.userSession.contains(username);
    }
}
