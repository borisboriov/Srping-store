package com.soskin.store.springstore.core.controllers;


import com.soskin.store.springstore.api.core.OrderDetailsDto;
import com.soskin.store.springstore.api.core.OrderDto;
import com.soskin.store.springstore.api.core.OrderItemDto;
import com.soskin.store.springstore.core.converters.OrderConverter;
import com.soskin.store.springstore.core.converters.OrderItemConverter;
import com.soskin.store.springstore.core.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class OrdersController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;
    private final OrderItemConverter orderItemConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username, @RequestBody OrderDetailsDto orderDetailsDto) {
        orderService.createOrder(username, orderDetailsDto);
    }
    @GetMapping
    public List<OrderDto> getCurrentUserOrders(@RequestHeader String username) {
        return orderService.findOrdersByUsername(username).stream()
                .map(orderConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping("/recommendations")
    public List<OrderItemDto> getRecommendation() {
        List<OrderItemDto> orderItem = orderService.findAllByQuantity();
        return orderItem;
//тут пришлось сделать лимит (а не в запросе в бд), т.к. в hql "limit" исчез с третей версии хибера.
//и в ангуляре, вроде, как нету цикла for i что бы ограничить вывод объектов на экран пятью штуками, а, как быстро иначе решить я не нашел.
    }
}

