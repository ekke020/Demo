//package com.backend.demo.controllers;
//
//import com.backend.demo.models.Order;
//import com.backend.demo.models.Product;
//import com.backend.demo.models.User;
//import com.backend.demo.repositories.OrderRepository;
//import com.backend.demo.services.OrderService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(OrderController.class)
//class OrderControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private OrderService orderService;
//
//    private Order o1;
//
//
//    @BeforeEach
//    public void init(){
//        User u1 = new User(1L, "Adam Andersson", "adam@mail.com");
//        User u2 = new User(2L, "Berit Bengtsson", "Berit@mail.com");
//        User u3 = new User(3L, "Carl Carlsson", "Carl@mail.com");
//
//        Product p1 = new Product(4L, "Apple");
//        Product p2 = new Product(5L, "Banana");
//        Product p3 = new Product(6L, "Mango");
//
//        o1 = new Order(7L, u1, List.of(p1, p2, p3));
//        Order o2 = new Order(8L, u2, List.of(p1, p1, p1, p1));
//        Order o3 = new Order(9L, u3, List.of(p2, p2, p2, p3, p3));
//
//        when(orderService.findById(7L)).thenReturn(o1);
//        when(orderService.getAll()).thenReturn(Set.of(o1, o2, o3));
//        when(orderService.findAllByUser(1L)).thenReturn(Set.of(o1));
//        when(orderService.save(o1)).thenReturn(o1);
//    }
//
//    @Test
//    void getAllOrders() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/order")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(3));
//    }
//
//    @Test
//    void getOrderByUserId() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/order/by/1")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
//    }
//
//    @Test
//    void addOrder() throws Exception {
//        mvc.perform( MockMvcRequestBuilders
//                        .post("/order/new")
//                        .content(new ObjectMapper().writeValueAsString(o1))
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(7L));
//    }
//
//
//}
