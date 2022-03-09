package com.example.week1.user.request;

import com.example.week1.rest.JsonConvertible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

@Getter @Setter @NoArgsConstructor
public class LoginRequest implements JsonConvertible {
    private String username;
    private String password;

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
}
