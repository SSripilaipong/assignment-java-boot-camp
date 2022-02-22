package com.example.week1.unit.payment;

import com.example.week1.payment.DefaultPaymentMethodRepository;
import com.example.week1.payment.PaymentMethod;
import com.example.week1.payment.PaymentMethodRepository;
import com.example.week1.payment.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.week1.unit.payment.PaymentDummyFactory.getDummyDefaultPaymentMethod;
import static com.example.week1.unit.payment.PaymentDummyFactory.getDummyPaymentMethod;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private DefaultPaymentMethodRepository defaultPaymentMethodRepository;

    @Mock
    private PaymentMethodRepository paymentMethodRepository;

    @Test
    void shouldLoadDefaultPaymentMethodOfAUser() {
        PaymentService paymentService = getPaymentServiceWithMock();
        when(defaultPaymentMethodRepository.findById("MyUsername"))
                .thenReturn(Optional.of(getDummyDefaultPaymentMethod()));

        PaymentMethod paymentMethod = paymentService.getMyDefaultPaymentMethod("MyUsername");

        assertEquals(getDummyPaymentMethod(), paymentMethod);
    }

    @Test
    void shouldSetDefaultPaymentMethodOfAUser() {
        PaymentService paymentService = getPaymentServiceWithMock();

        paymentService.setMyDefaultPaymentMethod("MyUsername", getDummyPaymentMethod());

        verify(defaultPaymentMethodRepository).save(getDummyDefaultPaymentMethod());
    }

    @Test
    void shouldGetPaymentMethodFromId() {
        PaymentService paymentService = getPaymentServiceWithMock();
        when(paymentMethodRepository.findById(1234)).thenReturn(Optional.of(getDummyPaymentMethod()));

        PaymentMethod paymentMethod = paymentService.getPaymentMethod(1234);

        assertEquals(getDummyPaymentMethod(), paymentMethod);
    }

    @Test
    void shouldCheckIfAUserIsAnOwnerOfThePaymentMethod() {
        PaymentService paymentService = getPaymentServiceWithMock();
        when(paymentMethodRepository.findById(1234)).thenReturn(Optional.of(getDummyPaymentMethod()));

        boolean isMine = paymentService.isMyPaymentMethod("MyUsername", 1234);

        assertTrue(isMine);
    }

    private PaymentService getPaymentServiceWithMock() {
        PaymentService paymentService = new PaymentService();
        paymentService.setDefaultPaymentMethodRepository(defaultPaymentMethodRepository);
        paymentService.setPaymentMethodRepository(paymentMethodRepository);
        return paymentService;
    }
}
