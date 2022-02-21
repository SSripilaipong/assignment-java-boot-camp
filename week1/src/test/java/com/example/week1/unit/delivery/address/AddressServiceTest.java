package com.example.week1.unit.delivery.address;

import com.example.week1.delivery.ReceiverInfo;
import com.example.week1.delivery.address.Address;
import com.example.week1.delivery.ReceiverInfoRepository;
import com.example.week1.delivery.address.AddressService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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
        Address myAddress = new Address("Name", "Address", "12345", "District", "Province", "0123456789");
        when(receiverInfoRepository.findById("MyUsername"))
                .thenReturn(Optional.of(new ReceiverInfo("MyUsername", myAddress)));

        Address defaultAddress = addressService.getMyDefaultAddress("MyUsername");

        assertEquals(myAddress, defaultAddress);
    }
}
