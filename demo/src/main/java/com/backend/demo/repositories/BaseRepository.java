package com.backend.demo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Set;

@NoRepositoryBean
public interface BaseRepository<T> extends CrudRepository<T, Long> {

    Set<T> findAll();


}
