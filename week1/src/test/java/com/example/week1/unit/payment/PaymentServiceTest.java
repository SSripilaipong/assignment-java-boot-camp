package com.example.week1.unit.payment;

import com.example.week1.payment.DefaultPaymentMethodRepository;
import com.example.week1.payment.PaymentMethod;
import com.example.week1.payment.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.week1.unit.payment.PaymentDummyFactory.getDummyDefaultPaymentMethod;
import static com.example.week1.unit.payment.PaymentDummyFactory.getDummyPaymentMethod;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private DefaultPaymentMethodRepository defaultPaymentMethodRepository;

    @Test
    void shouldLoadDefaultAddressOfAUser() {
        PaymentService paymentService = getPaymentServiceWithMock();
        when(defaultPaymentMethodRepository.findById("MyUsername"))
                .thenReturn(Optional.of(getDummyDefaultPaymentMethod()));

        PaymentMethod paymentMethod = paymentService.getMyDefaultPaymentMethod("MyUsername");

        assertEquals(getDummyPaymentMethod(), paymentMethod);
    }

    private PaymentService getPaymentServiceWithMock() {
        PaymentService paymentService = new PaymentService();
        paymentService.setPaymentMethodRepository(defaultPaymentMethodRepository);
        return paymentService;
    }
}
