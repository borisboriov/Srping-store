package com.soskin.store.springstore.core.repositories;

import com.soskin.store.springstore.api.core.OrderItemDto;
import com.soskin.store.springstore.core.entities.Order;
import com.soskin.store.springstore.core.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o where o.username = ?1")
    List<Order> findAllByUsername(String username);

//    @Query(value = "SELECT * FROM ORDER_ITEMS WHERE created_at BETWEEN '2022-03-11 00:00:01' AND '2022-03-11 22:09:30'  order BY quantity desc  limit  5", nativeQuery = true)
//    List<OrderItem> findAllByQuantity();


    @Query("select o from OrderItem o where o.createdAt between current_date AND current_timestamp order by o.quantity desc")
    List<OrderItem> findAllByQuantity();
}


//@Query(nativeQuery = true, value = "SELECT * FROM ORDER_ITEMS WHERE created_at BETWEEN '2022-03-11 00:00:01' AND '2022-03-11 23:09:30'  order BY quantity desc")


//@Query("select o from OrderItem o where o.createdAt between current_date AND current_timestamp order by o.quantity ")
//List<OrderItem> findAllByQuantity();