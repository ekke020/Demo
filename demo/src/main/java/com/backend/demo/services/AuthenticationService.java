package com.backend.demo.services;

import com.backend.demo.models.User;
import com.backend.demo.repositories.UserRepository;
import com.backend.demo.security.Jwt;
import com.backend.demo.security.SecurityUser;
import com.backend.demo.services.dto.TokenDto;
import com.backend.demo.services.dto.LoginDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final Jwt jwt;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new SecurityUser(user);
    }

    public TokenDto login(LoginDto dto) throws ResponseStatusException {
        Optional<User> Optional = userRepository.findByEmail(dto.getEmail());
        if (Optional.isPresent()) {
            User user = Optional.get();
            if (passwordService.matches(dto.getPassword(), user.getPassword())) {
                SecurityUser securityUser = new SecurityUser(user);
                return new TokenDto(jwt.generateToken(securityUser));
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password and email does not match");
    }
}
