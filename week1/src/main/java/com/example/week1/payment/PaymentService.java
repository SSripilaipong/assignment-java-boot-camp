package com.example.week1.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    public PaymentMethod getMyDefaultPaymentMethod(String username) {
        return new PaymentMethod();
    }
}
