package com.example.week1.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    DefaultPaymentMethodRepository defaultPaymentMethodRepository;

    public PaymentMethod getMyDefaultPaymentMethod(String username) {
        return defaultPaymentMethodRepository.findById(username)
                .map(DefaultPaymentMethod::getPaymentMethod).orElse(null);
    }

    public void setDefaultPaymentMethodRepository(DefaultPaymentMethodRepository defaultPaymentMethodRepository) {
        this.defaultPaymentMethodRepository = defaultPaymentMethodRepository;
    }

    public void setMyDefaultPaymentMethod(String username, PaymentMethod paymentMethod) {
        // TODO: implement
    }
}
