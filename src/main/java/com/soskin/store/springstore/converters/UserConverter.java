package com.soskin.store.springstore.converters;

import com.soskin.store.springstore.dto.UserDto;
import com.soskin.store.springstore.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;


@Slf4j
@Component
public class UserConverter {
    PasswordEncoder passwordEncoder;

    public User dtoToEntity(UserDto userDto) {
        String hashedPassword = passwordEncoder.encode(userDto.getPassword());
        User user = new User(userDto.getId(), userDto.getUsername(), hashedPassword,  userDto.getEmail(), LocalDateTime.now(), LocalDateTime.now());
        log.error("???____________________????" + user);
        return user;
    }

    public UserDto entityToDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getEmail());
    }

}
