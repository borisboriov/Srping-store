package com.soskin.store.spring.cart.integrations;


import com.soskin.store.spring.cart.exceptions.CoreServiceIntegrationException;
import com.soskin.store.springstore.api.core.ProductDto;
import com.soskin.store.springstore.api.exceptions.CoreServiceAppError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductsServiceIntegration {

    private final WebClient coreServiceWebClient;

    public Optional<ProductDto> getProductDtoByID(Long id) {
        ProductDto productDto = coreServiceWebClient.get()
                .uri(uriBuilder -> uriBuilder.path("/api/v1/products/{id}")
                        .build(id))
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.is4xxClientError(),  // HttpStatus::is4xxClientError
                        clientResponse -> clientResponse.bodyToMono(CoreServiceAppError.class).map(
                                body -> {
                                    if (body.getCode().equals(CoreServiceAppError.CoreServiceErrors.PRODUCT_NOT_FOUND.name())) {
                                        return new CoreServiceIntegrationException("An incorrect request to the product service was made : the product was not found!!!!!!!!!!!!!!!!!!");
                                    }
                                    return new CoreServiceIntegrationException("An incorrect request to the products service was made: the reason is unknown??????????????????????");
                                }//todo тут шо попало надо
                        )

                )
                .onStatus(
                        httpStatus -> httpStatus.is5xxServerError(),  // HttpStatus::is4xxClientError
                        clientResponse -> clientResponse.bodyToMono(CoreServiceAppError.class).map(
                                body -> {
                                    if (body.getCode().equals(CoreServiceAppError.CoreServiceErrors.PRODUCT_NOT_FOUND.name())) {
                                        return new CoreServiceIntegrationException("An incorrect request to the product service was made : the product was not found!!!!!!!!!!!!!!!!!!");
                                    }
                                    return new CoreServiceIntegrationException("An incorrect request to the products service was made: the reason is unknown??????????????????????");
                                }//todo тут шо попало надо
                        )

                )
//                .onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new CartServiceIntegrationException("Выполнен некорректный запрос к сервису корзин")))
//                .onStatus(HttpStatus::is5xxServerError, clientResponse -> Mono.error(new CartServiceIntegrationException("Сервис корзин сломался")))
                .bodyToMono(ProductDto.class)
                .block();
        return Optional.ofNullable(productDto);
    }
}

