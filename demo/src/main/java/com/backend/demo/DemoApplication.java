package com.backend.demo;

import com.backend.demo.security.Jwt;
import com.backend.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@ConfigurationPropertiesScan("com.backend.demo.config")
public class DemoApplication {

    @Autowired
    private Jwt jwt;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}


    @Bean
    public CommandLineRunner demo(UserService userService) {
		return (args) -> {
			System.out.println(jwt.getJWTToken("Johan Ekman"));
		};

	}
}
