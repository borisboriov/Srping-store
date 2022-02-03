package com.soskin.store.springstore.converters;


import com.soskin.store.springstore.dto.ProductDto;
import com.soskin.store.springstore.entities.Product;
import org.springframework.stereotype.Component;


@Component
public class ProductConverter {

    public Product dtoToEntity(ProductDto productDto){
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getCost(), productDto.getRate());
    }

    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getRate());
    }
}