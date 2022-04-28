//package com.backend.demo.controllers;
//
//import com.backend.demo.models.Product;
//import com.backend.demo.services.ProductService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(ProductController.class)
//class ProductControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ProductService productService;
//
//    private Product product;
//
//    @BeforeEach
//    public void init() {
//        Set<Product> products = new HashSet<>() {{
//                add(new Product(1L, "Mango"));
//                add(new Product(2L, "Banana"));
//                add(new Product(3L, "Apple"));
//        }};
//        List<Product> pList = new ArrayList<>(products);
//        when(productService.getAll()).thenReturn(products);
//        when(productService.findById(1L)).thenReturn(pList.get(0));
//        product = new Product(4L, "Pear");
//        when(productService.save(product))
//                .thenReturn(product);
//    }
//
//    @Test
//    public void shouldReturnThreeProducts() throws Exception {
//        mockMvc.perform(get("/product"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.*", hasSize(3)))
//                .andDo(print());
//    }
//
//    @Test
//    public void addShouldSaveProduct() throws Exception {
//        String jsonObject = new ObjectMapper().writeValueAsString(product);
//        mockMvc.perform(post("/product/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonObject))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Pear"))
//                .andExpect(jsonPath("$.id").value(4L))
//                .andDo(print());
//    }
//
//    @Test
//    public void getByIdShouldReturnProduct() throws Exception {
//        mockMvc.perform(get("/product/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Mango"))
//                .andExpect(jsonPath("$.id").value(1))
//                .andDo(print());
//    }
//
//    @Test
//    public void deleteByIdShouldRemoveProduct() throws Exception {
//        mockMvc.perform(delete("/product/remove/1"))
//                .andExpect(status().isNoContent())
//                .andDo(print());
//        verify(productService, times(1)).deleteById(any());
//    }
//}
