package com.sie.demo.security.auth;

import com.sie.demo.dto.LoginUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan
 * @description:
 * @date 2020-08-05 16:38:50
 */

@Component
public class TokenUtil {
    private static final String SECRET_KEY = "a1dfa5das";

    private static final long EXPIRED_TIME = 1000*60*60;


    // 生成令牌
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(Claims.SUBJECT, userDetails.getUsername());
        claims.put(Claims.ISSUED_AT, new Date());
        return generateToken(claims);
    }

    public String getUsernameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            System.out.println("claims = " + claims.toString());
            username = claims.getSubject();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
        return username;
    }

    public Boolean isTokenExpired(String token) throws  Exception{
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            new Throwable(e);
        }
        return true;
    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    // 验证令牌
    public Boolean validateToken(String token, UserDetails userDetails) throws Exception {
        LoginUser user = (LoginUser) userDetails;
        String username = getUsernameFromToken(token);
        return (username.equals(user.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis()+ EXPIRED_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public Claims getClaimsFromToken(String token) throws Exception {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            new Throwable(e);
        }
        return claims;
    }

}
