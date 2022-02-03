package com.soskin.store.springstore.controllers;

import com.soskin.store.springstore.dto.Cart;
import com.soskin.store.springstore.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/add/{id}")
    public void addProductToCart(@PathVariable Long id) {
        cartService.addProductByIdToCart(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.getCurrentCart().clear();
    }

    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable Long id) {
        log.error("----------------" + id);
        cartService.deleteByID(id);
    }
}
