package com.backend.demo.services;


import com.backend.demo.error.exceptions.EntityNotFoundException;
import com.backend.demo.repositories.BaseRepository;
import org.modelmapper.ModelMapper;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseService<T, S, R extends BaseRepository<T>> {

    protected final R repository;
    protected final ModelMapper mapper = new ModelMapper();
    private final Class<T> clazz;

    public BaseService(R repository, Class<T> clazz) {
        this.repository = repository;
        this.clazz = clazz;
    }

    public Set<S> getAll() {
        return repository.findAll().stream().map(this::mapToDto).collect(Collectors.toSet());
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public S findById(Long id) throws EntityNotFoundException {
         T t = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(clazz, "ID", id.toString()));
         return mapToDto(t);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    protected abstract S mapToDto(T t);

    protected abstract T mapToModel(S s);


}
