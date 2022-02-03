package com.soskin.store.springstore.repositories;

import com.soskin.store.springstore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {


    List<Product> findAllByPriceBetween(Integer min, Integer max);

    List<Product> findAllByPriceGreaterThan(Integer min);

    List<Product> findAllByPriceIsLessThan(Integer max);
}