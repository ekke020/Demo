package com.backend.demo.security.filters;

import com.backend.demo.error.exceptions.FilterException;
import com.backend.demo.security.Jwt;
import com.backend.demo.services.AuthenticationService;
import com.backend.demo.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final List<String> ignoredPaths = List.of("/login", "/user/add");
    private final String header = "Authorization";
    private final String prefix = "Bearer ";
    private final Jwt jwt;
    private final AuthenticationService authenticationService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
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
        String jws = request.getHeader(header).replace(prefix, "");
        try {
            jwt.isTokenExpired(jws);
            UserDetails user = authenticationService.loadUserByUsername(jwt.extractEmail(jws));
            var upa = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            upa.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(upa);
        } catch (ExpiredJwtException e) {
            throw new FilterException("Error validating access token: Session has expired.", 401);
        } catch (MalformedJwtException e) {
            throw new FilterException("Error validating access token: Malformed token.", 401);
        }
    }

}
