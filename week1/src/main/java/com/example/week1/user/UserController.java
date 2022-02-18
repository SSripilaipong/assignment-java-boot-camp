package com.example.week1.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse login(@RequestBody LoginRequest request) {
        String token = userService.login(request.getUsername(), request.getPassword());
        return new LoginResponse(token);
    }

    @GetMapping("/user/me")
    public CurrentUserResponse getCurrentUser(@RequestHeader("Authorization") String token) {
        return new CurrentUserResponse(userService.getUsernameFromToken(token));
    }
}
