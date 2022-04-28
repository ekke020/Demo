package com.backend.demo.controllers;
import com.backend.demo.services.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

public abstract class BaseController<T, S, V extends BaseService<T, S, ?>> {

    protected final V service;

    public BaseController(V service) {
        this.service = service;
    }

    @GetMapping(value = {"","/"})
    public Set<S> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public S getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/remove/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }


}
