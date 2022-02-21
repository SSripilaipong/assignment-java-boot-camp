package com.example.week1.unit.delivery.address;

import com.example.week1.delivery.address.Address;
import com.example.week1.delivery.ReceiverInfoRepository;
import com.example.week1.delivery.address.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.week1.unit.delivery.ReceiverInfoDummyFactory.getDummyAddress;
import static com.example.week1.unit.delivery.ReceiverInfoDummyFactory.getDummyInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private ReceiverInfoRepository receiverInfoRepository;

    @Test
    void shouldLoadDefaultAddressOfAUser() {
        AddressService addressService = new AddressService();
        addressService.setReceiverInfoRepository(receiverInfoRepository);
        when(receiverInfoRepository.findById("MyUsername")).thenReturn(Optional.of(getDummyInfo()));

        Address defaultAddress = addressService.getMyDefaultAddress("MyUsername");

        assertEquals(getDummyAddress(), defaultAddress);
    }
}
