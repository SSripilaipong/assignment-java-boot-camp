package com.example.week1.user.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class LoginFailedResponse {
    private String message;

    public LoginFailedResponse(String message) {
        this.message = message;
    }

}
