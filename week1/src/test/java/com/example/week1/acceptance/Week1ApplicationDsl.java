package com.example.week1.acceptance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Week1ApplicationDsl {

    @Autowired
    private Week1ApplicationRestApiDriver driver;

    public void userLogin(String username, String password) {
        driver.loginWithUsernameAndPassword(username, password);
    }

    public boolean confirmUserLogin(String username) {
        String currentUsername = driver.getCurrentUsername();
        return username.equals(currentUsername);
    }
}
