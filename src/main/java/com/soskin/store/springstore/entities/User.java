package com.soskin.store.springstore.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "order_id")
    private Long orderId;


    @OneToMany(mappedBy = "user")
    private List<Order> orderList;



    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;



//    @ManyToMany
//    @JoinTable(name = "orders",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private Collection<Product> products;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public User(Long id, String username, String hashedPassword, String email, LocalDateTime now, LocalDateTime now1) {
    }
//
//    public  User (UserDto userDto){
//        this.id = userDto.getId();
//        this.username = userDto.getUsername();
//        this.password = userDto.getPassword();
//        this.email = userDto.getEmail();
//        this.roles = roles.addAll(Role)
//    }
}