package com.backend.demo;

import com.backend.demo.services.PasswordService;
import lombok.AllArgsConstructor;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;

@ManagedBean
@AllArgsConstructor
public class PostRunner {

    private final PasswordService passwordService;

    @PostConstruct
    public void init() {
        String hash = passwordService.hashPassword("hej1234");
        System.out.println(hash);
        System.out.println(passwordService.matches("hej1234", hash));
    }

}
