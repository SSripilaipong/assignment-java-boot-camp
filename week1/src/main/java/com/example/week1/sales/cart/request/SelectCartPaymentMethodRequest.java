package com.example.week1.sales.cart.request;

import com.example.week1.rest.JsonConvertible;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class SelectCartPaymentMethodRequest implements JsonConvertible {

    private Integer paymentMethodId;

    public SelectCartPaymentMethodRequest() {
    }

    public SelectCartPaymentMethodRequest(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    @Override
    public String toJsonString() {
        JSONObject json = new JSONObject();
        try {
            json.put("paymentMethodId", paymentMethodId);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return json.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectCartPaymentMethodRequest that = (SelectCartPaymentMethodRequest) o;
        return Objects.equals(paymentMethodId, that.paymentMethodId);
    }

    public Integer getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Integer paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }
}
