package com.backend.demo.services.dto;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

@Data
public class ProductCreationDto {

    @NotEmpty(message = "Product must have a name")
    private String name;
    @Digits(integer = 100, fraction = 2)
    private Double price;
    @NotEmpty(message = "Product must have a base64 string")
    private String base64;

}
