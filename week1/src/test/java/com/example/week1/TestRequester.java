package com.example.week1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

@Component
public class TestRequester {

    @Autowired
    private TestRestTemplate rest;

    public <T, R extends JsonConvertible> ResponseEntity<T> post(String url, R requestObject, Class<T> responseClass) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(requestObject.toJsonString(), headers);

        return rest.postForEntity(url, request, responseClass);
    }

    public <T, R extends JsonConvertible> ResponseEntity<T> getWithToken(
            String url, String token, Class<T> responseClass) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        return rest.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), responseClass);
    }
}
