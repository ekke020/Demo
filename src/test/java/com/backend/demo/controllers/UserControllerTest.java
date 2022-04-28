//package com.backend.demo.controllers;
//
//import com.backend.demo.models.Product;
//import com.backend.demo.models.User;
//import com.backend.demo.repositories.UserRepository;
//import com.backend.demo.services.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Optional;
//import java.util.Set;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private UserService mockUserService;
//
//    private User u1;
//
//    @BeforeEach
//    public void init() {
//        u1 = new User(1L, "Adam Andersson", "adam@mail.com");
//        User u2 = new User(2L, "Berit Bengtsson", "Berit@mail.com");
//        User u3 = new User(3L, "Carl Carlsson", "Carl@mail.com");
//
//        when(mockUserService.findById(1L)).thenReturn(u1);
//        when(mockUserService.getAll()).thenReturn(Set.of(u1, u2, u3));
//        when(mockUserService.save(u1)).thenReturn(u1);
//    }
//
//    @Test
//    public void getUserById() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                        .get("/user/{id}", 1L)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
//    }
//
//    @Test
//    public void getAllUsers() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/user")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3));
//    }
//
//    @Test
//    public void addUser() throws Exception {
//        mvc.perform(MockMvcRequestBuilders
//                        .post("/user/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(new ObjectMapper().writeValueAsString(u1)))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L));
//    }
//}
