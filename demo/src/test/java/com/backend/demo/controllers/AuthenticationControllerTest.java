package com.backend.demo.controllers;

import com.backend.demo.services.AuthenticationService;
import com.backend.demo.services.dto.LoginDto;
import com.backend.demo.services.dto.TokenDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class AuthenticationControllerTest {

    @MockBean
    private AuthenticationService mockService;

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void init() {
        TokenDto tokenDto = new TokenDto("1234");
        when(mockService.login(any())).thenReturn(tokenDto);
    }

    @Test
    void loginWithoutCredentialsReturns400() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/login")).andExpect(status().isBadRequest()).andDo(print());
    }

    @Test
    void loginWithCredentialsReturn200() throws Exception {
        LoginDto dto = new LoginDto("test@test.com", "test1234");
        mvc.perform(MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto))
        ).andExpect(status().isOk()).andDo(print());
    }

}