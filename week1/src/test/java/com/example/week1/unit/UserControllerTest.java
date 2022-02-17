package com.example.week1.unit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void shouldReturnTokenWhenLoginWithUsernameAndPassword() {
        LoginRequest loginRequest = new LoginRequest("Santhapon", "Admin1234");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(loginRequest.toJsonString(), headers);

        LoginSuccessResponse response = rest.postForObject("/login", request, LoginSuccessResponse.class);
        Assertions.assertNotEquals(null, response.getToken());
    }
}
