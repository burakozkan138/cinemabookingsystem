package com.burakozkan138.cinemabookingsystem.utils;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.burakozkan138.cinemabookingsystem.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTUtils {
  private final SecretKey SECRET_KEY;
  private final long EXPIRATION_TIME;

  public JWTUtils() {
    this.SECRET_KEY = Keys.hmacShaKeyFor(
        Decoders.BASE64.decode("testsecretkey".repeat(30)));
    this.EXPIRATION_TIME = 86400000L;
  }

  public String genereateToken(User user) {
    return Jwts.builder()
        .subject(user.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SECRET_KEY)
        .compact();
  }

  public String generateRefreshToken(User user) {
    return Jwts.builder()
        .subject(user.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 7))
        .signWith(SECRET_KEY)
        .compact();
  }

  public String extractUserName(String token) {
    return extractClaims(token, Claims::getSubject);
  }

  private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
    return claimsTFunction
        .apply(Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload());
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUserName(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  public boolean isTokenExpired(String token) {
    return extractClaims(token, Claims::getExpiration).before(new Date());
  }
}
