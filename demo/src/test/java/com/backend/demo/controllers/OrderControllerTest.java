package com.backend.demo.controllers;

import com.backend.demo.models.Order;
import com.backend.demo.models.Product;
import com.backend.demo.models.User;
import com.backend.demo.services.OrderService;
import com.backend.demo.services.dto.OrderCreationDto;
import com.backend.demo.services.dto.OrderDto;
import com.backend.demo.services.dto.ProductDto;
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

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    @BeforeEach
    public void init(){
        UserDto u1 = new UserDto(1L, "Adam Andersson", "adam@mail.com");

        ProductDto p1 = new ProductDto(4L, "Apple", 12.0, "kjawdoaodkp");
        ProductDto p2 = new ProductDto(5L, "Banana", 15.0, "akmpwdajondia");
        ProductDto p3 = new ProductDto(6L, "Mango", 20.0, "nuawldnadmadjo");

        OrderDto o1 = new OrderDto(47.0, List.of(p1,p2,p3), new Date(), u1);

        when(orderService.findById(1L)).thenReturn(o1);
        when(orderService.getAll()).thenReturn(Set.of(o1));
        when(orderService.findAllByUser(1L)).thenReturn(Set.of(o1));
        when(orderService.save(any())).thenReturn(o1);
    }

    @Test
    void getAllOrders() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/order")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
    }

    @Test
    void getOrderByUserId() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/order/by/user/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1));
    }

    @Test
    void addOrder() throws Exception {
        OrderCreationDto order = new OrderCreationDto();
        order.setUserId(1L);
        order.setProductIds(List.of(4L, 5L, 6L));
        mvc.perform(MockMvcRequestBuilders
                        .post("/order/add")
                        .content(new ObjectMapper().writeValueAsString(order))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
