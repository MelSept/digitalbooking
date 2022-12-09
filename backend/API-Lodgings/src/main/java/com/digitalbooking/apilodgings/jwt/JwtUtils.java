package com.digitalbooking.apilodgings.jwt;

import com.digitalbooking.apilodgings.service.user.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${digitalbooking.app.jwtSecret}")
    private String jwtSecret;

    @Value("${digitalbooking.app.jwtExpirationMM}")
    private int jwtExpirationMM;

    SecretKey key;


    public String generateJwtToken(Authentication authentication) {

        key = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecret));

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        JwsClock jwsClock = new JwsClock();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(jwsClock.now())
                .setExpiration(jwsClock.getMinutes(jwtExpirationMM))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (JwtException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
