package com.example.week1.unit.user;

import com.example.week1.TestRequester;
import com.example.week1.user.exception.LoginFailedException;
import com.example.week1.user.UserService;
import com.example.week1.user.request.LoginRequest;
import com.example.week1.user.response.CurrentUserResponse;
import com.example.week1.user.response.LoginSuccessResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        LoginSuccessResponse response =
                requester.post("/login", loginRequest, LoginSuccessResponse.class).getBody();

        assert response != null;
        Assertions.assertEquals("MyToken", response.getToken());
    }

    @Test
    public void shouldReturnUnauthorizedStatusCodeWhenLoginFailed() {
        Mockito.when(userService.login("MyUsername", "MyPassword"))
                .thenThrow(new LoginFailedException("MyUsername"));

        LoginRequest loginRequest = new LoginRequest("MyUsername", "MyPassword");
        ResponseEntity<Object> response =
                requester.post("/login", loginRequest, Object.class);

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void shouldReturnCurrentUser() {
        Mockito.when(userService.getUsernameFromToken("MyToken")).thenReturn("MyUsername");

        CurrentUserResponse currentUser =
                requester.getWithToken("/user/me", "MyToken", CurrentUserResponse.class).getBody();

        assert currentUser != null;
        Assertions.assertEquals("MyUsername", currentUser.getUsername());
    }
}
