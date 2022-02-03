package com.soskin.store.springstore.repositories.specifications;

import com.soskin.store.springstore.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    //check method parameter if problem
    public static Specification<Product> rateGreaterOrEqualsThan(Integer rate) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("rate"), rate);
    }

    public static Specification<Product> rateLessOrEqualsThan(Integer rate) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("rate"), rate);
    }

    public static Specification<Product> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));

    }

}
