package com.soskin.store.springstore.controllers;

import com.soskin.store.springstore.converters.OrderConverter;
import com.soskin.store.springstore.dto.OrderDto;
import com.soskin.store.springstore.entities.Order;
import com.soskin.store.springstore.entities.User;
import com.soskin.store.springstore.service.OrderService;
import com.soskin.store.springstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<HttpStatus> createNewOrder(@RequestBody OrderDto orderDto, Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        Order order = orderConverter.dtoToEntity(orderDto, user);
        orderService.saveNewOrder(principal.getName(), order);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
