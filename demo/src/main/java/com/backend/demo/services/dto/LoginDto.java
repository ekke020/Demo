package com.backend.demo.services.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginDto {

    @Email
    @NotEmpty
    private final String email;
    @NotEmpty
    private final String password;
}
