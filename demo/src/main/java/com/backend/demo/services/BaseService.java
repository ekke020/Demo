package com.backend.demo.services;


import com.backend.demo.repositories.BaseRepository;
import org.modelmapper.ModelMapper;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseService<T, S, R extends BaseRepository<T>> {

    protected final R repository;
    protected final ModelMapper mapper = new ModelMapper();

    public BaseService(R repository) {
        this.repository = repository;
    }

    public Set<S> getAll() {
        return repository.findAll().stream().map(this::mapToDto).collect(Collectors.toSet());
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public abstract S findById(Long id);

    public void deleteAll(){
        repository.deleteAll();
    }

    protected abstract S mapToDto(T t);

    protected abstract T mapToModel(S s);


}
