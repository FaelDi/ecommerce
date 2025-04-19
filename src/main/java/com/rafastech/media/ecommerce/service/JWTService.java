package com.rafastech.media.ecommerce.service;
import com.rafastech.media.ecommerce.dto.UserInfoDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {

    private static final Long EXPIRATION_DATE = 30L;
    private static SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static SecretKey secretKeyRefresh = Keys.secretKeyFor(SignatureAlgorithm.HS256);

//    public String generateRefreshToken(String username) {
//        return generateTokenBase(username,secretKeyRefresh,60l);
//    }

    public String generateToken(UserInfoDTO user) {
        return generateTokenBase(user,secretKey,EXPIRATION_DATE);
    }

    private String generateTokenBase(UserInfoDTO user, SecretKey key, Long minutosExpiracao) {
        LocalDateTime dataAtual = LocalDateTime.now();
        LocalDateTime dataExpiracao = dataAtual.plusMinutes(minutosExpiracao);
        return Jwts.builder()
                .setIssuedAt(new Date(dataAtual.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
                .setExpiration(new Date(dataExpiracao.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
                .setSubject(user.getUsername())
                .signWith(key)
                .compact();
    }

    public Boolean validToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .isSigned(token);
    }

    public Boolean validRefreshToken(String refreshToken) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKeyRefresh)
                .build()
                .isSigned(refreshToken);
    }

    public String getUsernameByToken(String token) {
        return getUsernameByTokenBase(token,secretKey);
    }


    public String getUsernameByRefreshToken(String refreshToken) {
        return getUsernameByTokenBase(refreshToken,secretKeyRefresh);
    }

    private String getUsernameByTokenBase(String token, SecretKey key) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}