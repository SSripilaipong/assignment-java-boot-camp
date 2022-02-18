package com.example.week1.user;

import com.example.week1.user.request.LoginRequest;
import com.example.week1.user.response.CurrentUserResponse;
import com.example.week1.user.response.LoginSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginSuccessResponse login(@RequestBody LoginRequest request) {
        String token = userService.login(request.getUsername(), request.getPassword());
        return new LoginSuccessResponse(token);
    }

    @GetMapping("/user/me")
    public CurrentUserResponse getCurrentUser(@RequestHeader("Authorization") String token) {
        return new CurrentUserResponse(userService.getUsernameFromToken(token));
    }
}
