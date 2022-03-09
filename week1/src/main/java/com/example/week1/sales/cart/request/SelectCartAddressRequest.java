package com.example.week1.sales.cart.request;

import com.example.week1.rest.JsonConvertible;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONException;
import org.json.JSONObject;

@Getter @Setter @NoArgsConstructor
public class SelectCartAddressRequest implements JsonConvertible {

    private Integer addressId;

    public SelectCartAddressRequest(Integer addressId) {
        this.addressId = addressId;
    }

    @Override
    public String toJsonString() {
        JSONObject json = new JSONObject();
        try {
            json.put("addressId", addressId);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return json.toString();
    }

}
