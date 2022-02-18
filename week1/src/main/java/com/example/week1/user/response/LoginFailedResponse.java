package com.example.week1.user.response;

public class LoginFailedResponse {
    private String message;

    public LoginFailedResponse() {
    }

    public LoginFailedResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
