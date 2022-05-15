package com.backend.demo.security;

import com.backend.demo.error.exceptions.FilterException;
import com.backend.demo.models.User;
import com.backend.demo.repositories.UserRepository;
import com.backend.demo.services.PasswordService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class LoginAuthentication implements AuthenticationProvider {

    private final PasswordService passwordService;
    private final UserRepository userRepository;

    public LoginAuthentication(PasswordService passwordService, UserRepository userRepository) {
        this.passwordService = passwordService;
        this.userRepository = userRepository;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("Inside LoginAuthentication");
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new FilterException("Password and email does not match", 401));

         if (passwordService.matches(password, user)) {
             return new UsernamePasswordAuthenticationToken(email, password, new ArrayList<>());
         }
        throw new FilterException("Password and email does not match", 401);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.println("I'm in here!");
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
