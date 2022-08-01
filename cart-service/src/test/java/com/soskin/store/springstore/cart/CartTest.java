package com.soskin.store.springstore.cart;


import com.soskin.store.spring.cart.SpringWebCartsApplication;
import com.soskin.store.spring.cart.integrations.ProductsServiceIntegration;
import com.soskin.store.spring.cart.models.Cart;
import com.soskin.store.spring.cart.services.CartService;
import com.soskin.store.springstore.api.core.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.redis.core.RedisTemplate;


import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest(classes = {SpringWebCartsApplication.class})
@Slf4j
public class CartTest {

    @Autowired
    private CartService cartService;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @MockBean
    private ProductsServiceIntegration productsServiceIntegration;

    @BeforeEach
    public void initCart() {
        Cart cart = new Cart();
        ProductDto productDto = new ProductDto(1L, "Shake",  BigDecimal.valueOf(100.00));
        cart.add(productDto);
        String cartKey = "1";
        redisTemplate.opsForValue().set(cartKey, cart);
    }

    @Test
    public void testGetCurrentCart() {
        Assertions.assertEquals(BigDecimal.valueOf(100.00), cartService.getCurrentCart("1").getTotalPrice());
    }


    @Test
    public void addToCartTest() {
        ProductDto productDto = new ProductDto();
        productDto.setId(1L);
        productDto.setTitle("Milk");
        productDto.setPrice(BigDecimal.valueOf(100.00));

        Mockito.doReturn(Optional.of(productDto)).when(productsServiceIntegration).getProductDtoByID(1L);
        cartService.addToCart("1", 1L);
        cartService.addToCart("1", 1L);
        cartService.addToCart("1", 1L);
        Mockito.verify(productsServiceIntegration, Mockito.times(3)).getProductDtoByID(ArgumentMatchers.eq(1L));
        Assertions.assertEquals(BigDecimal.valueOf(400.00), cartService.getCurrentCart("1").getTotalPrice());
        Assertions.assertEquals(1, cartService.getCurrentCart("1").getItems().size());
    }

    @Test
    public void merge() {
        ProductDto productDto = new ProductDto(2L, "Potato", BigDecimal.valueOf(200.00));
        Mockito.doReturn(Optional.of(productDto)).when(productsServiceIntegration).getProductDtoByID(2L);
        cartService.addToCart("2", 2L);
        cartService.merge("1", "2");
        Assertions.assertEquals(BigDecimal.valueOf(300.00), cartService.getCurrentCart("1").getTotalPrice());
    }

    @Test
    public void clearCartTest() {
        cartService.clearCart("1");
        Assertions.assertEquals(0, cartService.getCurrentCart("1").getItems().size());
    }

}
