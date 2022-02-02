package com.soskin.store.springstore.controllers;


import com.soskin.store.springstore.converters.UserConverter;
import com.soskin.store.springstore.dto.UserDto;
import com.soskin.store.springstore.entities.User;
import com.soskin.store.springstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    UserConverter userConverter;

    @PostMapping
    public UserDto saveNewUser(@RequestBody UserDto userDto) {
        log.error("!!!!--------------------------------" + userDto); //данные с фронта приходят правильные
        User user = userConverter.dtoToEntity(userDto);              // но вот тут выскакивает nullpointer и я так и не понял, как исправить
        user = userService.save(user);
        return userConverter.entityToDto(user);
    }

}
