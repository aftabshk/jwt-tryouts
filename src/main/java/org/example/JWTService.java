package org.example;

import io.jsonwebtoken.*;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class JWTService {

    public String generateToken(Map<String, String> claims, KeyPair keyPair) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject("aftab")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(new Date(2025, 12, 12))
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
    }

    public Jwt<?, ?> verifyToken(String token, PublicKey key) {
        JwtParser jwtParser = Jwts.parser().verifyWith(key).build();

        try {
            Jwt<?, ?> claims = jwtParser.parse(token);
            return claims;
        } catch (MalformedJwtException exception) {
            System.out.println("Invalid jwt token!");
            throw exception;
        }
    }

    public String generateToken(Map<String, String> claims, PrivateKey privateKey) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject("aftab")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(new Date(2025, 12, 12))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }
}
