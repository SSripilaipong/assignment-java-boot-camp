package com.example.week1.unit;

import com.example.week1.TestRequester;
import com.example.week1.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRequester requester;

    @MockBean
    private UserService userService;

    @Test
    public void shouldReturnTokenWhenLoginWithUsernameAndPassword() {
        Mockito.when(userService.login("MyUsername", "MyPassword")).thenReturn("MyToken");

        LoginRequest loginRequest = new LoginRequest("MyUsername", "MyPassword");
        LoginSuccessResponse response = requester.post("/login", loginRequest, LoginSuccessResponse.class);
        Assertions.assertEquals("MyToken", response.getToken());
    }
}
