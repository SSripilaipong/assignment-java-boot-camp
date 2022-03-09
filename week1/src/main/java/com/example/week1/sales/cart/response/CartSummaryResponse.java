package com.example.week1.sales.cart.response;

import com.example.week1.delivery.address.AddressService;
import com.example.week1.payment.PaymentService;
import com.example.week1.sales.cart.Cart;
import com.example.week1.sales.cart.CartItem;
import com.example.week1.sales.product.Product;
import com.example.week1.sales.product.ProductService;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
public class CartSummaryResponse {
    private ArrayList<CartItemResponse> items;
    private Double totalPrice;
    private String address;
    private String cardNumber;

    public CartSummaryResponse() {
        items = new ArrayList<>();
    }

    public static CartSummaryResponse fromCart(
            PaymentService paymentService, AddressService addressService, ProductService productService, Cart cart) {
        CartSummaryResponse response = new CartSummaryResponse();
        double totalPrice = 0.0;
        for(CartItem item : cart.getItems()) {
            Product product = productService.getProduct(item.getProductId());
            response.addItem(CartItemResponse.ofProduct(product, item.getQuantity()));
            totalPrice += product.getPrice() * item.getQuantity();
        }
        response.setTotalPrice(totalPrice);
        if(cart.getAddressId() != null) {
            response.setAddress(addressService.getAddress(cart.getAddressId()).getAddress());
        }
        if(cart.getPaymentMethodId() != null) {
            response.setCardNumber(paymentService.getPaymentMethod(cart.getPaymentMethodId()).getCardNumber());
        }
        return response;
    }

    private void addItem(CartItemResponse cartItemResponse) {
        items.add(cartItemResponse);
    }

}
