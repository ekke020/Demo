package com.backend.demo.repositories;

import com.backend.demo.models.User;

import java.util.Optional;


public interface UserRepository extends BaseRepository<User> {

    Optional<User> findByEmail(String email);

}
