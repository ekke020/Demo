package com.backend.demo.services.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreationDto {

    @NotEmpty(message = "Product must have a name")
    private String name;
    @Digits(integer = 100, fraction = 2)
    private Double price;
    @NotEmpty(message = "Product must have a base64 string")
    private String base64;

}
