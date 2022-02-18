package com.example.week1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
}
