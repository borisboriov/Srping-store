package com.soskin.store.springstore.service;

import com.soskin.store.springstore.entities.Order;
import com.soskin.store.springstore.entities.User;
import com.soskin.store.springstore.exceptions.ResourceNotFoundException;
import com.soskin.store.springstore.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final CartService cartService;


    @Transactional
    public void saveNewOrder(String name, Order order) {
        User user = userService.findByUsername(name).orElseThrow(() -> new ResourceNotFoundException("User not found: " + name));
        Long userId = user.getId();
        order.setTotalPrice(cartService.getCurrentCart().getTotalPrice());
        order.setUserId(userId);
        log.error("---------ORDER BEFORE SAVE   " + order);
        orderRepository.save(order);
        cartService.clear();
        log.error("---------ORDER afeter  SAVE  and clear  " + order);


    }

}
