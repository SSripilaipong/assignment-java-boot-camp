package com.example.week1.unit.user;

import com.example.week1.user.User;
import com.example.week1.user.UserRepository;
import com.example.week1.user.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void shouldRegisterNewUser() {
        userService.register("MyUsername", "MyPassword");

        ArgumentCaptor<User> arg = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(arg.capture());
        User registeredUser = arg.getValue();

        assertEquals("MyUsername", registeredUser.getUsername());
        assertEquals("MyPassword", registeredUser.getPassword());
    }
}