package com.backend.demo.security;

import com.backend.demo.config.Properties;
import com.backend.demo.error.exceptions.FilterException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.List;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final List<String> ignoredPaths = List.of("/user/login", "/user/add");
    private final String header = "Authorization";
    private final String prefix = "Bearer ";
    private final Properties properties;

    public JWTAuthorizationFilter(Properties properties) {
        this.properties = properties;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return ignoredPaths.stream().anyMatch(e -> e.matches(path));
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getHeader(header) == null) {
            throw new FilterException("Missing header: " + header, 400);
        }
        validateToken(request);
        filterChain.doFilter(request, response);
    }

    private void validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(header).replace(prefix, "");
        Key key = new SecretKeySpec(Base64.getDecoder().decode(properties.getJwtKey()),
                SignatureAlgorithm.HS512.getJcaName());
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwtToken);
        } catch (ExpiredJwtException e) {
            throw new FilterException("Error validating access token: Session has expired.", 401);
        } catch (MalformedJwtException e) {
            throw new FilterException("Error validating access token: Malformed token.", 401);
        }
        //TODO: Add UsernameAndPasswordToken class
    }

}
