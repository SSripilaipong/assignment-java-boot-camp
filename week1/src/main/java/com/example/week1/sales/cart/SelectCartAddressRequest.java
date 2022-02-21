package com.example.week1.sales.cart;

import com.example.week1.rest.JsonConvertible;
import org.json.JSONException;
import org.json.JSONObject;

public class SelectCartAddressRequest implements JsonConvertible {

    private Integer addressId;

    public SelectCartAddressRequest() {
    }

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

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
}
