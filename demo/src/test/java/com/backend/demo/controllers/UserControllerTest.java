package com.backend.demo.controllers;

import com.backend.demo.services.UserService;
import com.backend.demo.services.dto.UserCreationDto;
import com.backend.demo.services.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService mockUserService;


    @BeforeEach
    public void init() {
        UserDto u1 = new UserDto(1L, "Adam Andersson", "adam@mail.com");
        UserDto u2 = new UserDto(2L, "Berit Bengtsson", "Berit@mail.com");
        UserDto u3 = new UserDto(3L, "Carl Carlsson", "Carl@mail.com");

        when(mockUserService.findById(1L)).thenReturn(u1);
        when(mockUserService.getAll()).thenReturn(Set.of(u1, u2, u3));
        when(mockUserService.save(any())).thenReturn(u1);
    }

    @Test
    public void getUserById() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                        .get("/user/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }

    @Test
    public void getAllUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/user")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3));
    }

    @Test
    public void addUser() throws Exception {
        UserCreationDto u1 = new UserCreationDto();
        u1.setName("test");
        u1.setEmail("test@test.com");
        u1.setPassword("okimawdma91123");
        mvc.perform(MockMvcRequestBuilders
                        .post("/user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(u1)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
    }
}
