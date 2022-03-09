package com.example.week1.user.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CurrentUserResponse {
    private String username;

    public CurrentUserResponse(String username) {
        this.username = username;
    }

}
