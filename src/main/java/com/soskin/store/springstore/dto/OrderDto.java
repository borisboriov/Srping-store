package com.soskin.store.springstore.dto;


import com.soskin.store.springstore.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private Long userId;

    private int totalPrice;

    private String address;

    private String phone;

    public OrderDto(Long id, Long userId, int totalPrice, String address, String phone) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phone = phone;
    }
}
