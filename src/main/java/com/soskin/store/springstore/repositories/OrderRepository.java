package com.soskin.store.springstore.repositories;

import com.soskin.store.springstore.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Override
    <S extends Order> S save(S entity);

}
