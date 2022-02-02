package com.soskin.store.springstore.dto;


import com.soskin.store.springstore.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    private String title;

    private int cost;

    private int rate;

    public ProductDto(Product product) {
    }
}
