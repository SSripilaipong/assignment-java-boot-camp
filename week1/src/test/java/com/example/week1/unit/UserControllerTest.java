package com.example.week1.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void shouldReturnTokenWhenLoginWithUsernameAndPassword() {
        LoginRequest request = new LoginRequest("Santhapon", "Admin1234");
        LoginSuccessResponse response = rest.postForObject("/login", request, LoginSuccessResponse.class);
        Assertions.assertNotEquals(null, response.getToken());
    }
}
