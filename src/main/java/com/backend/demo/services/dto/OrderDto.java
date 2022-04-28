package com.backend.demo.services.dto;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    private Double totalPrice;
    private List<ProductDto> products;
    private Date createdAt;
    private UserDto UserDto;
}
