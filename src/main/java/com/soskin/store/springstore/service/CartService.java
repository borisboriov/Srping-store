package com.soskin.store.springstore.service;


import com.soskin.store.springstore.cart.Cart;
import com.soskin.store.springstore.converters.ProductConverter;
import com.soskin.store.springstore.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final Cart cart;
    private final ProductConverter productConverter;

    public List<Product> findAllInCart() {
        return cart.findAll();
    }

    public void addToCart(Product product) {
        cart.add(product);
    }

    public void deleteByID(Long id) {
        cart.deleteById(id);
    }

    public void clearCart() {
        cart.clear();
    }
}
