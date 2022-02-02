package com.soskin.store.springstore.service;


import com.soskin.store.springstore.dto.ProductDto;
import com.soskin.store.springstore.entities.Product;
import com.soskin.store.springstore.exceptions.ResourceNotFoundException;
import com.soskin.store.springstore.repositories.ProductRepository;
import com.soskin.store.springstore.specifications.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public void changeRate(Long productID, Integer delta) {
        Product product = productRepository.findById(productID).orElseThrow(() -> new ResourceNotFoundException("Unable to change product's rate. Product not found, id: " + productID));
        product.setRate(product.getRate() + delta);
    }

    public Page<Product> findAll(Integer minRate, Integer maxRate, String partTitle, Integer page) {
        Specification<Product> specification = Specification.where(null);
        if (minRate != null) {
            specification = specification.and(ProductSpecifications.rateGreaterOrEqualsThan(minRate));
        }
        if (maxRate != null) {
            specification = specification.and(ProductSpecifications.rateLessOrEqualsThan(maxRate));
        }
        if (partTitle != null) {
            specification = specification.and(ProductSpecifications.titleLike(partTitle));
        }
        return productRepository.findAll(specification, PageRequest.of(page - 1, 10));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteByID(Long id) {
        productRepository.deleteById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Not update! Product not found " + productDto.getId()));
        product.setCost(product.getCost());
        product.setTitle(product.getTitle());
        product.setRate(productDto.getRate());
        return product;
    }


}
