package com.soskin.store.springstore.dto;


import com.soskin.store.springstore.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;

    private String username;

    private String password;

    private String email;

    public UserDto(User user) {
        id = user.getId();
        username = getUsername();
        password = user.getPassword();
        email = user.getEmail();
    }
}
