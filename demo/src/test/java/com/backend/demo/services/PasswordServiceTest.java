package com.backend.demo.services;

import com.backend.demo.config.Properties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PasswordServiceTest {

    PasswordService passwordService;

    @Mock
    Properties properties;

    @BeforeEach
    void init() {
        passwordService = new PasswordService(properties);
        when(properties.getPepper()).thenReturn("QiWa4zEis0PsGgQCUAKZ");
    }

    @Test
    void hashedPasswordShouldHaveProperFormat() {
        String hash = passwordService.hashPassword("test1234");
        String[] parts = hash.split("\\|");
        assertTrue(parts[0].matches("[0-9]{1,2} [0-9]{1,2}"));
        assertEquals(96,parts[1].length());
    }

    @Test
    void samePasswordMatches() {
        String hash = passwordService.hashPassword("test1234");
        assertTrue(passwordService.matches("test1234", hash));
    }

    @Test
    void wrongPasswordDoesNotMatch() {
        String hash = passwordService.hashPassword("test1234");
        assertFalse(passwordService.matches("1234test", hash));
    }
}