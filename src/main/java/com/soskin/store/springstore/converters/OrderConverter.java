package com.soskin.store.springstore.converters;

import com.soskin.store.springstore.dto.OrderDto;
import com.soskin.store.springstore.entities.Order;
import com.soskin.store.springstore.entities.User;
import com.soskin.store.springstore.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {


    public Order dtoToEntity(OrderDto orderDto, User user) {
        return new Order(orderDto.getId(), orderDto.getTotalPrice(), orderDto.getAddress(), orderDto.getPhone(), user.getId());
    }

    public OrderDto entityToDto(Order order) {
        return new OrderDto(order.getId(), order.getUserId(), order.getTotalPrice(), order.getAddress(), order.getPhone());
    }
}
