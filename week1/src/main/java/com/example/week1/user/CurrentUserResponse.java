package com.example.week1.user;

public class CurrentUserResponse {
    private String username;

    public CurrentUserResponse() {
    }

    public CurrentUserResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
