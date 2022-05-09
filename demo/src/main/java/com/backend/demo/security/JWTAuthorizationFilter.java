package com.backend.demo.security;

import com.backend.demo.error.Error;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final String header = "Authorization";
    private final String prefix = "Bearer ";
    @Value("${variables.jwtKey}")
    private String secret;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader(header) == null) {
            addResponse(response, "Missing header: " + header);
            return;
        }
        try {
            validateToken(request);
        } catch (ExpiredJwtException e) {
            // TODO: Add more info about time here.
            addResponse(response, "Error validating access token: Session has expired.");
        } catch (MalformedJwtException e) {
            addResponse(response, "Error validating access token: Malformed token.");
        }
    }

    private void addResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Error error = new Error(HttpStatus.UNAUTHORIZED, message);
        response.getWriter().write(writeErrorAsJSON(error));
    }

    private String writeErrorAsJSON(Error error) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
        return mapper.writeValueAsString(error);
    }

    private void validateToken(HttpServletRequest request) throws ExpiredJwtException, MalformedJwtException {
        String jwtToken = request.getHeader(header).replace(prefix, "");
        Key key = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS512.getJcaName());
        Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
    }
}
