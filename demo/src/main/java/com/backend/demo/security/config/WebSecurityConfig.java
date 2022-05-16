package com.backend.demo.security.config;

import com.backend.demo.security.filters.ExceptionHandlerFilter;
import com.backend.demo.security.filters.JWTAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;

    public WebSecurityConfig(
            JWTAuthorizationFilter jwtAuthorizationFilter,
            ExceptionHandlerFilter exceptionHandlerFilter) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.exceptionHandlerFilter = exceptionHandlerFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests((auth) -> auth
                        .antMatchers("/user/login", "/user/add").permitAll()
                        .anyRequest().authenticated()

                )
                .addFilterBefore(exceptionHandlerFilter, LogoutFilter.class)
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
