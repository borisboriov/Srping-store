package com.soskin.store.springstore.recommendations.integrations;


import com.soskin.store.springstore.api.core.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CoreServiceIntegration {

    private final WebClient coreServiceWebClient;

//    public void clearUserCart(String username) {
//        coreServiceWebClient.get()
//                .uri("/api/v1/cart/0/clear")
//                .header("username", username)
//                .retrieve()
//                .toBodilessEntity()
//                .block();
//    }

    public List<OrderItemDto> getRecommendationItems() {
        List<OrderItemDto> orderItemDtos = coreServiceWebClient.get()
                .uri("/api/v1/orders/recommendations")
                .retrieve()
                .bodyToFlux(OrderItemDto.class )
                .collectList().block();
        return orderItemDtos;
    }
}
