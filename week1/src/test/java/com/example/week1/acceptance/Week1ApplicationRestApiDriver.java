package com.example.week1.acceptance;

import com.example.week1.TestRequester;
import com.example.week1.unit.user.CurrentUserResponse;
import com.example.week1.unit.user.LoginRequest;
import com.example.week1.unit.user.LoginSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Week1ApplicationRestApiDriver {

    @Autowired
    private TestRequester requester;

    private String userToken;

    public void loginWithUsernameAndPassword(String username, String password) {
        ResponseEntity<LoginSuccessResponse> response =
                requester.post("/login", new LoginRequest(username, password), LoginSuccessResponse.class);
        assert response.getStatusCode() == HttpStatus.OK && response.getBody() != null;
        userToken = response.getBody().getToken();
    }

    public String getCurrentUsername() {
        ResponseEntity<CurrentUserResponse> response =
                requester.getWithToken("/user/me", userToken, CurrentUserResponse.class);
        assert response.getStatusCode() == HttpStatus.OK && response.getBody() != null;
        return response.getBody().getUsername();
    }
}
