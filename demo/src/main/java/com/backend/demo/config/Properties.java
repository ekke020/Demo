package com.backend.demo.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


@ConfigurationProperties(prefix = "variables")
@ConstructorBinding
@Getter
public class Properties {

    private final String pepper;
    private final String jwtKey;

    public Properties(String pepper, String jwtKey) {
        this.pepper = pepper;
        this.jwtKey = jwtKey;
    }
}
