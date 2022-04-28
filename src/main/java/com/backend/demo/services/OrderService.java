package com.backend.demo.services;

import com.backend.demo.error.exceptions.EntityNotFoundException;
import com.backend.demo.models.Order;
import com.backend.demo.models.Product;
import com.backend.demo.models.User;
import com.backend.demo.repositories.OrderRepository;
import com.backend.demo.repositories.ProductRepository;
import com.backend.demo.repositories.UserRepository;
import com.backend.demo.services.dto.OrderCreationDto;
import com.backend.demo.services.dto.OrderDto;
import com.backend.demo.services.dto.ProductDto;
import com.backend.demo.services.dto.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService extends BaseService<Order, OrderDto, OrderRepository> {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository) {
        super(orderRepository);
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Set<OrderDto> findAllByUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return repository.findAllByUser(user)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toSet());
    }

    public OrderDto save(OrderCreationDto dto) {
        Order order= new Order();
        List<Product> products = new ArrayList<>();
        Optional<User> user = userRepository.findById(dto.getUserId());
        productRepository.findAllById(dto.getProductIds()).forEach(products::add);
        order.setUser(user.get());
        order.setProducts(products);
        repository.save(order);
        return mapToDto(order);
    }

    @Override
    public OrderDto findById(Long id) {
        Order order = repository.findById(id).orElse(null);
        if (order == null) {
            throw new EntityNotFoundException(Order.class, "ID", id.toString());
        }
        return mapToDto(order);
    }

    @Override
    protected OrderDto mapToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        Double totalPrice = order.getProducts().stream()
                .map(Product::getPrice)
                .reduce(Double::sum)
                .orElse(0.0);
        List<ProductDto> products = order.getProducts().stream()
                .map(product -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        UserDto user = mapper.map(order.getUser(), UserDto.class);
        orderDto.setUserDto(user);
        orderDto.setTotalPrice(totalPrice);
        orderDto.setCreatedAt(order.getCreatedAt());
        orderDto.setProducts(products);
        return orderDto;
    }

    @Override
    protected Order mapToModel(OrderDto orderDto) {
        return null;
    }

}
