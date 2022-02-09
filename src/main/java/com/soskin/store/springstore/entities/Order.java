package com.soskin.store.springstore.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

//    @Column(name = "product_id")
//    private Long productId;

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private User user;

    public Order(Long id, int totalPrice, String address, String phone, Long userId) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.address = address;
        this.phone = phone;
        this.userId = userId;
    }

//    @ManyToMany
//    @JoinTable(name = "orders",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private Collection<Product> products;
}
