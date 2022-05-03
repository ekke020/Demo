package com.backend.demo.controllers;

import com.backend.demo.services.ProductService;
import com.backend.demo.services.dto.ProductCreationDto;
import com.backend.demo.services.dto.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void init() {
        ProductDto p1 = new ProductDto(1L, "Mango", 12.5, "kljkö098123m123:a");
        ProductDto p2 = new ProductDto(2L, "Banana", 15.0, "ojmadwaiawda?981");
        ProductDto p3 = new ProductDto(3L, "Apple", 10.99, "8ohyawda1+9010");
        when(productService.getAll()).thenReturn(Set.of(p1,p2,p3));
        when(productService.findById(1L)).thenReturn(p1);
        when(productService.save(any()))
                .thenReturn(p3);
    }

    @Test
    public void shouldReturnThreeProducts() throws Exception {
        mockMvc.perform(get("/product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(3)));
    }

    @Test
    public void addShouldSaveProduct() throws Exception {
        ProductCreationDto p = new ProductCreationDto("Apple", 10.99, "8ohyawda1+9010");
        String jsonObject = new ObjectMapper().writeValueAsString(p);
        mockMvc.perform(post("/product/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonObject))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Apple"))
                .andExpect(jsonPath("$.id").value(3L));
    }

    @Test
    public void getByIdShouldReturnProduct() throws Exception {
        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Mango"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void deleteByIdShouldRemoveProduct() throws Exception {
        mockMvc.perform(delete("/product/remove/1"))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).deleteById(any());
    }
}
