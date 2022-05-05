package com.backend.demo.controllers;

import com.backend.demo.models.User;
import com.backend.demo.services.PasswordService;
import com.backend.demo.services.UserService;
import com.backend.demo.services.dto.UserCreationDto;
import com.backend.demo.services.dto.UserDto;
import com.backend.demo.services.dto.UserLoginDto;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController extends BaseController<User, UserDto, UserService> {


    public UserController(UserService userService) {
        super(userService);
    }

    @PostMapping("/add")
    public ResponseEntity<UserDto> add(@Valid @RequestBody UserCreationDto userCreationDto) {
        UserDto dto = service.save(userCreationDto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        UserDto dto = service.login(userLoginDto);
        return ResponseEntity.status(201).body(dto);
    }

}


