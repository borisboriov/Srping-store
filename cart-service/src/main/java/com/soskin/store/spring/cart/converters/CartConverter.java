package com.soskin.store.spring.cart.converters;


import com.soskin.store.spring.cart.models.Cart;
import com.soskin.store.springstore.api.carts.CartDto;
import com.soskin.store.springstore.api.carts.CartItemDto;
import com.soskin.store.springstore.api.core.ProductDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartConverter {
    public CartDto modelToDto(Cart cart) {
        List<CartItemDto> cartItemDtos = cart.getItems().stream().map(it ->
                new CartItemDto(it.getProductId(), it.getProductTitle(), it.getQuantity(), it.getPricePerProduct(), it.getPrice())
        ).collect(Collectors.toList());
        CartDto cartDto = new CartDto(cartItemDtos, cart.getTotalPrice());
        return cartDto;
    }
}
