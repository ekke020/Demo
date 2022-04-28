package com.backend.demo.controllers;

import com.backend.demo.services.dto.OrderCreationDto;
import com.backend.demo.services.dto.OrderDto;
import com.backend.demo.models.Order;
import com.backend.demo.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;


@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController extends BaseController<Order, OrderDto, OrderService>{

    public OrderController(OrderService orderService) {
        super(orderService);
    }

    @GetMapping("/by/user/{id}")
    public Set<OrderDto> getOrderByUserId(@PathVariable Long id) {
        return service.findAllByUser(id);
    }

    @PostMapping("/add")
    public ResponseEntity<OrderDto> add(@Valid @RequestBody OrderCreationDto orderCreationDto) {
        OrderDto dto = service.save(orderCreationDto);
        return ResponseEntity.ok(dto);
    }
}
