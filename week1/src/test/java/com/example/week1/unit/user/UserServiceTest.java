package com.example.week1.unit.user;

import com.example.week1.user.User;
import com.example.week1.user.UserRepository;
import com.example.week1.user.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    public void shouldRegisterNewUser() {
        UserService userService = new UserService();
        userService.setUserRepository(userRepository);
        ArgumentCaptor<User> arg = ArgumentCaptor.forClass(User.class);

        userService.register("MyUsername", "MyPassword");

        verify(userRepository).save(arg.capture());
        User registeredUser = arg.getValue();

        assertEquals("MyUsername", registeredUser.getUsername());
        assertEquals("MyPassword", registeredUser.getPassword());
    }
}