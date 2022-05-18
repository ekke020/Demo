package com.backend.demo.services;

import com.backend.demo.models.Authority;
import com.backend.demo.models.User;
import com.backend.demo.repositories.UserRepository;
import com.backend.demo.security.Authorities;
import com.backend.demo.services.dto.UserCreationDto;
import com.backend.demo.services.dto.UserDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserService extends BaseService<User, UserDto, UserRepository> {

    private final PasswordService passwordService;

    public UserService(UserRepository userRepository, PasswordService passwordService) {
        super(userRepository, User.class);
        this.passwordService = passwordService;
    }

    public UserDto save(UserCreationDto dto) {
        User user = mapper.map(dto, User.class);
        user.setPassword(passwordService.hashPassword(dto.getPassword()));
        user.getAuthorities().add(new Authority(Authorities.BASIC.name()));
        try {
            user = repository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email is already registered");
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
