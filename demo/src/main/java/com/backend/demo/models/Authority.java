package com.backend.demo.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JoinColumn(name = "user")
    @ManyToOne
    private User user;
}
