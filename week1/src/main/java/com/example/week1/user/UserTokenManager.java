package com.example.week1.user;

import org.springframework.stereotype.Component;

@Component
public class UserTokenManager {
    public String generateTokenFromUsername(String username) {
        return String.format("I am: %s", username);
    }
}
