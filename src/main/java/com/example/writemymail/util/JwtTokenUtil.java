package com.example.writemymail.util;

import com.example.writemymail.error.NotValidTokenException;
import com.example.writemymail.service.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.example.writemymail.domain.enumerated.TokenType.ACCESS;
import static com.example.writemymail.domain.enumerated.TokenType.REFRESH;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.accessTokenExpirationMs}")
    private long accessTokenExpirationMs;

    @Value("${jwt.refreshTokenExpirationMs}")
    private long refreshTokenExpirationMs;
    private final UserService userService;

    public String generateAccessToken(UserDetails userDetails) {
        return generateToken(userDetails, accessTokenExpirationMs, ACCESS.name());
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return generateToken(userDetails, refreshTokenExpirationMs,REFRESH.name());
    }
    private String generateToken(UserDetails userDetails, long expirationMs, String tokenType) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationMs);
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("type", tokenType)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return getPayloadByToken(token).getSubject();
        }catch (ExpiredJwtException e){
            throw new NotValidTokenException("Токен истек");
        }catch (Exception e){
            throw new NotValidTokenException("Неверный токен");
        }
    }

    public boolean isRefreshToken(String token) {
        Claims claims = getPayloadByToken(token);
        return claims.containsKey("type") && claims.get("type").equals(REFRESH.name());
    }

    public UserDetails findUserDetailsByJwt(String jwt) {
        return userService.loadUserByUsername(extractUsername(jwt));
    }

    private Claims getPayloadByToken(String token){
        return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getPayload();
    }

}



