package com.soskin.store.springstore.api.carts;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "CartItemDto model")
public class CartItemDto {

    @Schema(description = "Product ID", required = true, example = "1")
    private Long productId;

    @Schema(description = "Product title", required = true, example = "Fish")
    private String productTitle;

    @Schema(description = "Quantity", required = true, example = "10")
    private int quantity;

    @Schema(description = "Price per product", required = true, example = "10.00")
    private BigDecimal pricePerProduct;

    @Schema(description = "Price", required = true, example = "120.00")
    private BigDecimal price;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPricePerProduct() {
        return pricePerProduct;
    }

    public void setPricePerProduct(BigDecimal pricePerProduct) {
        this.pricePerProduct = pricePerProduct;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CartItemDto() {
    }

    public CartItemDto(Long productId, String productTitle, int quantity, BigDecimal pricePerProduct, BigDecimal price) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.pricePerProduct = pricePerProduct;
        this.price = price;
    }
}