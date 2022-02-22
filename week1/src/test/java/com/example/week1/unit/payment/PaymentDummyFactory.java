package com.example.week1.unit.payment;

import com.example.week1.payment.DefaultPaymentMethod;
import com.example.week1.payment.PaymentMethod;
import com.example.week1.payment.response.PaymentMethodResponse;

public class PaymentDummyFactory {
    public static PaymentMethod getDummyPaymentMethod() {
        return new PaymentMethod(1, "creditCard", "MyName", "Number 1234", "12/34", "555");
    }

    public static PaymentMethodResponse getDummyPaymentMethodResponse() {
        return new PaymentMethodResponse(1, "creditCard", "MyName", "Number 1234", "12/34", "555");
    }

    public static DefaultPaymentMethod getDummyDefaultPaymentMethod() {
        return new DefaultPaymentMethod("MyUsername", getDummyPaymentMethod());
    }
}
