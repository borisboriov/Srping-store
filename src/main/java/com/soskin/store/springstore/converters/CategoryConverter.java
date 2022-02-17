package com.soskin.store.springstore.converters;


import com.soskin.store.springstore.dto.CategoryDto;
import com.soskin.store.springstore.dto.ProductDto;
import com.soskin.store.springstore.entities.Category;
import com.soskin.store.springstore.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {

    public CategoryDto entityToDto(Category category) {
        return new CategoryDto(category.getTitle());
    }

//    public Category dtoToEntity(CategoryDto categoryDto) {
//        return new Category(categoryDto.getTitle());
//    }
}