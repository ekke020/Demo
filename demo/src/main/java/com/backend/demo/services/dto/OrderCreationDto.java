package com.backend.demo.services.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderCreationDto {


    @NotEmpty(message="Order must contain at least one product")
    private List<Long> productIds = new ArrayList<>();
    @NotNull(message="User ID must be provided")
    private Long userId;

}