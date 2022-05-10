package com.soskin.store.springstore.api.core;


import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "OrderDto model")
public class OrderDto {

    @Schema(description = "Order ID", required = true, example = "1")
    private Long id;

    @Schema(description = "Username", required = true, example = "Adi")
    private String username;

    @Schema(description = "Items", required = true, example = "5")
    private List<OrderItemDto> items;

    @Schema(description = "Total price", required = true, example = "1000.00")
    private BigDecimal totalPrice;

    @Schema(description = "Address", required = true, example = "Tel Aviv, Ibn Gabirol 12")
    private String address;

    @Schema(description = "Phone number", required = true, example = "+972-568567455")
    private String phone;

    public OrderDto() {
    }

    public OrderDto(Long id, String username, List<OrderItemDto> items, BigDecimal totalPrice, String address, String phone) {
        this.id = id;
        this.username = username;
        this.items = items;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
