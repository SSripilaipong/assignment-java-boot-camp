package com.example.week1.sales.cart.response;

import com.example.week1.delivery.address.AddressResponse;
import com.example.week1.sales.cart.Cart;
import com.example.week1.sales.cart.CartItem;
import com.example.week1.sales.product.Product;
import com.example.week1.sales.product.ProductService;

import java.util.ArrayList;

public class CartSummaryResponse {
    private ArrayList<CartItemResponse> items;
    private Double totalPrice;

    public CartSummaryResponse() {
        items = new ArrayList<>();
    }

    public static CartSummaryResponse fromCart(ProductService productService, Cart cart) {
        CartSummaryResponse response = new CartSummaryResponse();
        double totalPrice = 0.0;
        for(CartItem item : cart.getItems()) {
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

    public String getAddress() {
        return null;
    }
}
