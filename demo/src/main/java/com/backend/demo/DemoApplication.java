package com.backend.demo;

import com.backend.demo.security.Jwt;
import com.backend.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;


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
			UserDetails user = userService.loadUserByUsername("test@test.com");
			System.out.println(user);
//			String jws = jwt.generateToken(user);
//			System.out.println(jws);
//			System.out.println(jwt.extractEmail(jws));
//			System.out.println(jwt.extractExpiration(jws));
//			System.out.println(jwt.isTokenExpired(jws));
		};

	}
}
