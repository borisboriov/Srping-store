package com.soskin.store.springstore.controllers;


import com.soskin.store.springstore.converters.ProductConverter;
import com.soskin.store.springstore.dto.ProductDto;
import com.soskin.store.springstore.entities.Product;
import com.soskin.store.springstore.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductConverter productConverter;


    // Этот метод выдает такую ошибку:
//Failed to convert value of type 'java.lang.String' to required type 'java.lang.Long';
// nested exception is java.lang.NumberFormatException: For input string: "undefined"]
// не могу понять почему в метод не долетает корректные данные
    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable Long id) {
        log.error(id + "!!!");
        cartService.deleteByID(id);
    }

    @PostMapping("/add/")
    public List<Product> addProductToCart(@RequestBody ProductDto productDto) {
        Product product = productConverter.dtoToEntity(productDto);
        cartService.addToCart(product);
        return cartService.findAllInCart();
    }

    @DeleteMapping
    public List<Product> clearCart() {
        cartService.clearCart();
        return cartService.findAllInCart();
    }
}
