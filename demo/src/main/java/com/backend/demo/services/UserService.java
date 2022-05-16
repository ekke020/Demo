package com.backend.demo.services;

import com.backend.demo.error.exceptions.EntityNotFoundException;
import com.backend.demo.models.User;
import com.backend.demo.repositories.UserRepository;
import com.backend.demo.security.Jwt;
import com.backend.demo.services.dto.UserCreationDto;
import com.backend.demo.services.dto.UserDto;
import com.backend.demo.services.dto.UserLoginDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class UserService extends BaseService<User, UserDto, UserRepository> {

    private final PasswordService passwordService;
    private final Jwt jwt;

    public UserService(UserRepository userRepository, PasswordService passwordService, Jwt jwt) {
        super(userRepository);
        this.passwordService = passwordService;
        this.jwt = jwt;
    }

    public UserDto save(UserCreationDto dto) {
        User user = mapper.map(dto, User.class);
        user.setSalt(passwordService.generateSalt());
        user.setHash(passwordService.getHashedPassword(dto.getPassword(), user.getSalt()));
        try {
            user = repository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
        }
        return mapToDto(user);
    }

    public UserDto login(UserLoginDto dto) {
        Optional<User> Optional = repository.findByEmail(dto.getEmail());
        if (Optional.isPresent()) {
            User user = Optional.get();
            String hash = passwordService.getHashedPassword(dto.getPassword(), user.getSalt());
            if (hash.equals(user.getHash())) {
//                user.setToken(jwt.generateToken(user));
                //TODO: CLEAN THIS UP
                return mapToDto(user);
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Password and email does not match");
    }


    @Override
    public UserDto findById(Long id) {
        User user = repository.findById(id).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException(User.class, "ID", id.toString());
        }
        return mapToDto(user);
    }

    @Override
    protected UserDto mapToDto(User user) {
        return mapper.map(user, UserDto.class);
    }

    @Override
    protected User mapToModel(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

}
