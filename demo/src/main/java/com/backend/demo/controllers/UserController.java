package com.backend.demo.controllers;

import com.backend.demo.models.User;
import com.backend.demo.services.UserService;
import com.backend.demo.services.dto.UserCreationDto;
import com.backend.demo.services.dto.UserDto;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

}


