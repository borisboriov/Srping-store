package com.soskin.store.springstore.auth;

import com.soskin.store.springstore.auth.entities.User;
import com.soskin.store.springstore.auth.repositories.UserRepository;
import com.soskin.store.springstore.auth.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest(classes = UserService.class)
public class AuthServiceTest {
    @Autowired
    UserService userService;
    @MockBean
    UserRepository userRepository;

    @Test
    public void userTest() {
        User userFromDB = new User();
        userFromDB.setId(1L);
        userFromDB.setUsername("bob");
        userFromDB.setEmail("bob_johnson@gmail.com");
        Mockito.doReturn(Optional.of(userFromDB)).when(userRepository).findByUsername("bob");

        User userBob = userService.findByUsername("bob").get();
        Assertions.assertNotNull(userBob);
        Assertions.assertEquals("bob_johnson@gmail.com", userBob.getEmail());
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("bob"));
    }

}
