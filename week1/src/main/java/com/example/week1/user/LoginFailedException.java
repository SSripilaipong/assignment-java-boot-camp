package com.example.week1.user;

public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String username) {
        super(String.format("login failed for user: %s", username));
    }
}
