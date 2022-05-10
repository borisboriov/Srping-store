package com.soskin.store.springstore.api.core;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;


@Schema(description = "Product model")
public class ProductDto {

    @Schema(description = "Product ID", required = true, example = "1")
    private Long id;

    @Schema(description = "Product name", required = true, maxLength = 255, minLength = 3, example = "Milk")
    private String title;

    @Schema(description = "Product price", required = true, example = "120")
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProductDto() {
    }

    public ProductDto(Long id, String title, BigDecimal price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }
}

