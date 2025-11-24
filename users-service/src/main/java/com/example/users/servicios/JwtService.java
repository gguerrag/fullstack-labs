package com.example.users.servicios;

import com.example.users.entidades.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // Clave simple para la demo (32+ caracteres)
    private final String SECRET = "clave-super-secreta-fullstack-labs-2025-123456";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    public String generateToken(User user) {
        long now = System.currentTimeMillis();
        long expiration = now + (1000 * 60 * 60); // 1 hora

        return Jwts.builder()
                .setSubject(user.getUsername() != null ? user.getUsername() : user.getEmail())
                .claim("email", user.getEmail())
                .claim("nombre", user.getNombre())
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(expiration))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
