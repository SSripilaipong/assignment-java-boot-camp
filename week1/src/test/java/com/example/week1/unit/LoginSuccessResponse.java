package com.example.week1.unit;

public class LoginSuccessResponse {
    private final String token;

    public LoginSuccessResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
