package com.example.week1.user.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class LoginSuccessResponse {
    private String token;

    public LoginSuccessResponse(String token) {
        this.token = token;
    }

}
