package com.example.week1.user;

public class UserTokenManager {
    public String generateTokenFromUsername(String username) {
        return String.format("I am: %s", username);
    }
}
