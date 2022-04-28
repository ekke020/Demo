package com.backend.demo.repositories;

import com.backend.demo.models.Order;
import com.backend.demo.models.User;

import java.util.Set;

public interface OrderRepository extends BaseRepository<Order> {

    Set<Order> findAllByUser(User user);
}
