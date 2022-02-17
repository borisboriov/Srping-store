package com.soskin.store.springstore.controllers;

import com.soskin.store.springstore.converters.CategoryConverter;
import com.soskin.store.springstore.converters.ProductConverter;
import com.soskin.store.springstore.dto.CategoryDto;
import com.soskin.store.springstore.entities.Category;
import com.soskin.store.springstore.services.CategoryService;
import com.soskin.store.springstore.services.ProductsService;
import com.soskin.store.springstore.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryConverter categoryConverter;


    @GetMapping
    public List<CategoryDto> getAllCategories() {
        return categoryService.findAll().stream().map(categoryConverter::entityToDto).collect(Collectors.toList());
    }


}
