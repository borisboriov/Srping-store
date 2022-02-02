package com.soskin.store.springstore.cart;

import com.soskin.store.springstore.entities.Product;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class Cart {

    private final List<Product> productList;


    public void add(Product product) {
        productList.add(product);
    }

    public List<Product> findAll() {
        return productList;
    }

    public void deleteById(Long id) {
        productList.remove(id);
    }

    public void clear() {
        productList.clear();
    }
}
