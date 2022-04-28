package com.backend.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Double price;

    @Lob
    private String base64;

    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product() {
    }
}
