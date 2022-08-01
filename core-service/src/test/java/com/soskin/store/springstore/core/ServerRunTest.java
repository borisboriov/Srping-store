package com.soskin.store.springstore.core;


import com.soskin.store.springstore.api.core.OrderDto;
import com.soskin.store.springstore.api.core.OrderItemDto;
import com.soskin.store.springstore.api.core.ProductDto;
import com.soskin.store.springstore.core.entities.Order;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServerRunTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void productsControllerTest() {
        //getProductBidId(Long id);
        ProductDto productDto = testRestTemplate.getForObject("http://localhost:8189/web-market-core/api/v1/products/1", ProductDto.class);
        Assertions.assertNotNull(productDto);
        Assertions.assertEquals("Milk", productDto.getTitle());
    }

    @Test
    public void ordersControllerTest() {
        //getCurrentUserOrders
        HttpHeaders headers = new HttpHeaders();
        headers.set("Username", "bob");
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>("body", headers);
//        ResponseEntity<OrderDto[]> orders = testRestTemplate.getForEntity("http://localhost:8189/web-market-core/api/v1/orders", OrderDto[].class);
//        System.out.println(Arrays.toString(orders.getBody()));

        //getOrderByID(Long id);
        List<OrderItemDto> orderItemDtoList = new ArrayList<>(
                List.of(new OrderItemDto(1L, "Milk", 1, BigDecimal.valueOf(100), BigDecimal.valueOf(100))));
        OrderDto orderDto = new OrderDto(1L, "bob", orderItemDtoList, BigDecimal.valueOf(100), "address", "1234556");
        ResponseEntity<Order> entity = testRestTemplate.getForEntity("http://localhost:8189/web-market-core/api/v1/orders/1", Order.class);
        Assertions.assertEquals(entity.getBody().getUsername(), orderDto.getUsername());
    }

    @Test
    public void httpTest() {
        HttpStatus status = testRestTemplate.execute("http://localhost:8189/web-market-core/api/v1/orders/1", HttpMethod.GET,
                request -> log.info(request.getHeaders().toString()),
                response -> {
                    log.info(response.getHeaders().toString());
                    return response.getStatusCode();
                });
        Assertions.assertTrue(status.is2xxSuccessful());
    }
}
