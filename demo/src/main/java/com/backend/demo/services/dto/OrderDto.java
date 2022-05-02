package com.backend.demo.services.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Double totalPrice;
    private List<ProductDto> products;
    private Date createdAt;
    private UserDto UserDto;
}
