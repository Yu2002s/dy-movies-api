package xyz.jdynb.dymovies.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtUtils {

    /**
     * jwt密钥
     */
    @Value("${jwt.key}")
    private String jwtKey;

    /**
     * 登录有效期
     */
    @Value("${jwt.expired}")
    private Duration expired;

    public String generateToken(Integer id) {
        long millis = expired.toMillis();
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", id);
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtKey)
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + millis))
                .compact();
    }

    public Integer parseToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtKey)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get("user_id", Integer.class);
        } catch (Exception e) {
            return null;
        }
    }
}
