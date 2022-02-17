package com.example.week1.unit;

import com.example.week1.JsonConvertible;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginRequest implements JsonConvertible {
    private final String username;
    private final String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String toJsonString() {
        JSONObject json = new JSONObject();
        try {
            json.put("username", username);
            json.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return json.toString();
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
