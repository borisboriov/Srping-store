package com.soskin.store.springstore.core.controllers;


import com.soskin.store.springstore.api.exceptions.ResourceNotFoundException;
import com.soskin.store.springstore.api.core.ProductDto;
import com.soskin.store.springstore.core.validators.ProductValidator;
import com.soskin.store.springstore.core.converters.ProductConverter;
import com.soskin.store.springstore.core.entities.Product;
import com.soskin.store.springstore.core.services.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
//@CrossOrigin("*")
@Tag(name = "Products", description = "methods for work with products")
public class ProductsController {
    private final ProductsService productsService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @Operation(
            summary = "Request for a products page",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )
            }
    )
    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "min_price", required = false) Integer minPrice,
            @RequestParam(name = "max_price", required = false) Integer maxPrice,
            @RequestParam(name = "title_part", required = false) String titlePart
    ) {
        if (page < 1) {
            page = 1;
        }
        return productsService.findAll(minPrice, maxPrice, titlePart, page).map(
                productConverter::entityToDto
        );
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Request to receive a product by id",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    )

            }
    )
    public ProductDto getProductById(@PathVariable @Parameter(description = "Product ID", required = true) Long id) {
        Product product = productsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return productConverter.entityToDto(product);
    }

    @Operation(
            summary = "Request to save new product",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200")

            }
    )
    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productsService.save(product);
        return productConverter.entityToDto(product);
    }

    @Operation(
            summary = "Request to update a product",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200")

            }
    )
    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productsService.update(productDto);
        return productConverter.entityToDto(product);
    }

    @Operation(
            summary = "Request to delete a product",
            responses = {
                    @ApiResponse(
                            description = "Success response", responseCode = "200")

            }
    )
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable @Parameter(description = "Product ID", required = true) Long id) {
        productsService.deleteById(id);
    }
}
