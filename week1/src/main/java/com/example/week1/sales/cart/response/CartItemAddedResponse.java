package com.example.week1.sales.cart.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CartItemAddedResponse {
    private String message;

    public CartItemAddedResponse(String message) {
        this.message = message;
    }

}
