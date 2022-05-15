package com.backend.demo.security;

import com.backend.demo.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTAuthorizationFilter jwtAuthorizationFilter;
    private final ExceptionHandlerFilter exceptionHandlerFilter;
    private final LoginAuthentication loginAuthentication;
    private final UserService userService;

    public WebSecurityConfig(
            JWTAuthorizationFilter jwtAuthorizationFilter,
            ExceptionHandlerFilter exceptionHandlerFilter,
            LoginAuthentication loginAuthentication, UserService userService) {
        this.jwtAuthorizationFilter = jwtAuthorizationFilter;
        this.exceptionHandlerFilter = exceptionHandlerFilter;
        this.loginAuthentication = loginAuthentication;
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(loginAuthentication)
                .userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .csrf().disable()
                .authorizeHttpRequests((auth) -> auth
                        .antMatchers("/user/login").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(exceptionHandlerFilter, LogoutFilter.class)
                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    @Bean
    protected AuthenticationManager authenticationManager()
            throws Exception {
        return super.authenticationManager();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors().and()
//                .csrf().disable()
//                .authorizeHttpRequests((auth) -> auth
//                        .antMatchers("/user/login").authenticated()
//                )
//                .addFilterBefore(new JWTExceptionFilter(), LogoutFilter.class)
//                .addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }

//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().antMatchers("/user/login", "/user/add");
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

//    @Bean
//    public void authenticationManager(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(loginAuthentication);
//    }
}
