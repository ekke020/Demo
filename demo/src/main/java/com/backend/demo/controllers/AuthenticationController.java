package com.backend.demo.controllers;

import com.backend.demo.services.AuthenticationService;
import com.backend.demo.services.dto.TokenDto;
import com.backend.demo.services.dto.LoginDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {
        TokenDto dto = service.login(loginDto);
        return ResponseEntity.status(200).body(dto);
    }

}
