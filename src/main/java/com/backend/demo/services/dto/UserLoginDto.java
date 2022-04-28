package com.backend.demo.services.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserLoginDto {

    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
}
