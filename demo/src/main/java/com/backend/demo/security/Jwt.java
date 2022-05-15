package com.backend.demo.security;


import com.backend.demo.config.Properties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class Jwt {

    //TODO: Move all JWT logic to this class

    private final Properties properties;
    private final Key key;

    public Jwt(Properties properties) {
        this.properties = properties;
        key = new SecretKeySpec(Base64.getDecoder().decode(properties.getJwtKey()),
                SignatureAlgorithm.HS512.getJcaName());
    }

    public String extractEmail(String token) {
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
        //TODO: Fix this method.
        return null;
    }

    public String getJWTToken(String username) {
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(key)
                .compact();
        return "Bearer " + token;
    }

}
