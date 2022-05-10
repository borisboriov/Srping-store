package com.soskin.store.springstore.api.core;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "OrderDetailsDto model")
public class OrderDetailsDto {

    @Schema(description = "Address", required = true, example = "Tel Aviv, Ibn Gabirol 12")
    private String address;

    @Schema(description = "Phone number", required = true, example = "+972-568567455")
    private String phone;


    public OrderDetailsDto(String address, String phone) {
        this.address = address;
        this.phone = phone;
    }

    public OrderDetailsDto() {
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
