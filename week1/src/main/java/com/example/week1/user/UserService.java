package com.example.week1.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String login(String username, String password) {
        return username;
    }

}
