package com.example.week1.unit;

import com.example.week1.TestRequester;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRequester requester;

    @Test
    public void shouldReturnTokenWhenLoginWithUsernameAndPassword() {
        LoginRequest loginRequest = new LoginRequest("Santhapon", "Admin1234");
        LoginSuccessResponse response = requester.post("/login", loginRequest, LoginSuccessResponse.class);
        Assertions.assertNotEquals(null, response.getToken());
    }
}
