package com.backend.demo.services;

import com.backend.demo.error.exceptions.EntityNotFoundException;
import com.backend.demo.models.Product;
import com.backend.demo.models.User;
import com.backend.demo.repositories.ProductRepository;
import com.backend.demo.services.dto.ProductCreationDto;
import com.backend.demo.services.dto.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product, ProductDto, ProductRepository> {


    public ProductService(ProductRepository productRepository) {
        super(productRepository);
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = repository.findById(id).orElse(null);
        if (product == null) {
            throw new EntityNotFoundException(Product.class, "ID", id.toString());
        }
        return mapToDto(product);
    }

    public ProductDto save(ProductCreationDto dto) {
        Product product = mapper.map(dto, Product.class);
        product = repository.save(product);
        return mapToDto(product);
    }
    @Override
    protected ProductDto mapToDto(Product product) {
        return mapper.map(product, ProductDto.class);
    }

    @Override
    protected Product mapToModel(ProductDto productDto) {
        return mapper.map(productDto, Product.class);
    }
}
