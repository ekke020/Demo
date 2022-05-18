package com.backend.demo.security.config;

import com.backend.demo.services.PasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Encoder implements PasswordEncoder {

    private final PasswordService passwordService;

    public Encoder(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return passwordService.hashPassword(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordService.matches(rawPassword.toString(), encodedPassword);
    }
}
