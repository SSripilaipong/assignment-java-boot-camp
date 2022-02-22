package com.example.week1.payment;

import com.example.week1.payment.response.PaymentMethodResponse;
import com.example.week1.user.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    @Autowired
    UserTokenManager tokenManager;

    @Autowired
    PaymentService paymentService;

    @GetMapping("/payment/method/default")
    public PaymentMethodResponse getMyDefaultPaymentMethod(@RequestHeader("Authorization") String token) {
        return PaymentMethodResponse.fromPaymentMethod(
                paymentService.getMyDefaultPaymentMethod(tokenManager.decodeTokenToUsername(token)));
    }
}
