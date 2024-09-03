package com.jsl.shop_inn.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    Key getSignKey();
    Claims extractAllClaims(String token);
    <T> T extractClaim(String token, Function<Claims, T> resolver);
    Date extractExpiration(String token);
    String extractUsername(String token);
    boolean isTokenExpired(String token);
    boolean isTokenValid(String token, UserDetails userDetails);
    String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration);
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
    String generateToken(UserDetails userDetails);
}
