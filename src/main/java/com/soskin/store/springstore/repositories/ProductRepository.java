package com.soskin.store.springstore.repositories;

import com.soskin.store.springstore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {


    List<Product> findAllByCostBetween(Integer min, Integer max);

    List<Product> findAllByCostGreaterThan(Integer min);

    List<Product> findAllByCostIsLessThan(Integer max);
}