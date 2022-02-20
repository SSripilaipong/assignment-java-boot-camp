package com.example.week1.cart.response;

import com.example.week1.cart.CartItem;
import com.example.week1.cart.CartSummary;
import com.example.week1.product.Product;
import com.example.week1.product.ProductService;

import java.util.ArrayList;

public class CartSummaryResponse {
    private ArrayList<CartItemResponse> items;
    private Double totalPrice;

    public CartSummaryResponse() {
        items = new ArrayList<>();
    }

    public static CartSummaryResponse fromCartSummary(ProductService productService, CartSummary summary) {
        CartSummaryResponse response = new CartSummaryResponse();
        double totalPrice = 0.0;
        for(CartItem item : summary.getItems()) {
            Product product = productService.getProduct(item.getProductId());
            response.addItem(CartItemResponse.ofProduct(product, item.getQuantity()));
            totalPrice += product.getPrice() * item.getQuantity();
        }
        response.setTotalPrice(totalPrice);
        return response;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    private void addItem(CartItemResponse cartItemResponse) {
        items.add(cartItemResponse);
    }

    public ArrayList<CartItemResponse> getItems() {
        return items;
    }

    public void setItems(ArrayList<CartItemResponse> items) {
        this.items = items;
    }
}
