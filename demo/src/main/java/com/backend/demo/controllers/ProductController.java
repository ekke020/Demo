package com.backend.demo.controllers;

import com.backend.demo.models.Order;
import com.backend.demo.models.Product;
import com.backend.demo.services.dto.OrderCreationDto;
import com.backend.demo.services.dto.OrderDto;
import com.backend.demo.services.dto.ProductCreationDto;
import com.backend.demo.services.dto.ProductDto;
import com.backend.demo.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController extends BaseController<Product, ProductDto, ProductService>{


    public ProductController(ProductService productService) {
        super(productService);
    }

    @PostMapping("/add")
    public ProductDto add(@Valid @RequestBody ProductCreationDto productCreationDto) {
        return service.save(productCreationDto);
    }

}
