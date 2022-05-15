package com.backend.demo.controllers;

import com.backend.demo.models.User;
import com.backend.demo.services.UserService;
import com.backend.demo.services.dto.UserCreationDto;
import com.backend.demo.services.dto.UserDto;
import com.backend.demo.services.dto.UserLoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
        return ResponseEntity.status(200).body(dto);
    }

}


