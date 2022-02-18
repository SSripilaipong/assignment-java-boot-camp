package com.example.week1.user;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class UserTokenManager {
    public String generateTokenFromUsername(String username) {
        return String.format("Bearer %s", username);
    }

    public String decodeTokenToUsername(String token) {
        Scanner scanner = new Scanner(token);
        scanner.next();
        return scanner.next();
    }
}
