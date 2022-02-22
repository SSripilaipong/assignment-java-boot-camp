package com.example.week1.unit.payment;

import com.example.week1.TestRequester;
import com.example.week1.payment.PaymentService;
import com.example.week1.payment.response.PaymentMethodResponse;
import com.example.week1.user.UserTokenManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.week1.unit.payment.PaymentDummyFactory.getDummyPaymentMethod;
import static com.example.week1.unit.payment.PaymentDummyFactory.getDummyPaymentMethodResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaymentControllerTest {

    @MockBean
    private UserTokenManager tokenManager;

    @MockBean
    private PaymentService paymentService;

    @Autowired
    TestRequester requester;

    @Test
    void shouldLoadDefaultPaymentMethod() {
        when(tokenManager.decodeTokenToUsername("MyToken")).thenReturn("MyUsername");
        when(paymentService.getMyDefaultPaymentMethod("MyUsername")).thenReturn(getDummyPaymentMethod());

        PaymentMethodResponse response = requester.getWithToken(
                "/payment/method/default", "MyToken", PaymentMethodResponse.class).getBody();

        assertEquals(getDummyPaymentMethodResponse(), response);
    }

}
