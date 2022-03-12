package com.soskin.store.springstore.core.services;


import com.soskin.store.springstore.api.carts.CartDto;
import com.soskin.store.springstore.api.core.OrderItemDto;
import com.soskin.store.springstore.api.exceptions.ResourceNotFoundException;
import com.soskin.store.springstore.api.core.OrderDetailsDto;
import com.soskin.store.springstore.core.converters.OrderItemConverter;
import com.soskin.store.springstore.core.entities.OrderItem;

import com.soskin.store.springstore.core.integrations.CartsServiceIntegration;
import com.soskin.store.springstore.core.repositories.OrdersRepository;
import com.soskin.store.springstore.core.entities.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrdersRepository ordersRepository;
    private final CartsServiceIntegration cartsServiceIntegration;
    private final ProductsService productsService;
    private final OrderItemConverter orderItemConverter;

    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto) {
        CartDto currentCart = cartsServiceIntegration.getUserCart(username);
        Order order = new Order();
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        order.setUsername(username);
        order.setTotalPrice(currentCart.getTotalPrice());
        List<OrderItem> items = currentCart.getItems().stream()
                .map(o -> {
                    OrderItem item = new OrderItem();
                    item.setOrder(order);
                    item.setQuantity(o.getQuantity());
                    item.setPricePerProduct(o.getPricePerProduct());
                    item.setPrice(o.getPrice());
                    item.setProduct(productsService.findById(o.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found")));
                    return item;
                }).collect(Collectors.toList());
        order.setItems(items);
        ordersRepository.save(order);
        cartsServiceIntegration.clearUserCart(username);
    }

    public List<Order> findOrdersByUsername(String username) {
        return ordersRepository.findAllByUsername(username);
    }


    public List<OrderItemDto> findAllByQuantity() {
        return ordersRepository.findAllByQuantity().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList());
    }
    //ну и тут пришлось конвертер использовать....

}
