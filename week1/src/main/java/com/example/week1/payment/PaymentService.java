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
        defaultPaymentMethodRepository.save(new DefaultPaymentMethod(username, paymentMethod));
    }

    public boolean isMyPaymentMethod(String username, int paymentMethodId) {
        return false;  // TODO: implement
    }

    public PaymentMethod getPaymentMethod(int paymentMethodId) {
        return null; // TODO: implement
    }

    public void setPaymentMethodRepository(PaymentMethodRepository paymentMethodRepository) {
        // TODO: implement
    }

}
