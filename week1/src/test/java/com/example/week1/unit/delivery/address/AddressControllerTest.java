package com.example.week1.unit.delivery.address;

import com.example.week1.TestRequester;
import com.example.week1.delivery.address.AddressResponse;
import com.example.week1.delivery.address.AddressService;
import com.example.week1.user.UserTokenManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static com.example.week1.unit.delivery.ReceiverInfoDummyFactory.getDummyAddress;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressControllerTest {

    @MockBean
    private UserTokenManager tokenManager;

    @MockBean
    private AddressService addressService;

    @Autowired
    private TestRequester requester;

    @Test
    void shouldLoadDefaultAddress() {
        when(tokenManager.decodeTokenToUsername("MyToken")).thenReturn("MyUsername");
        when(addressService.getMyDefaultAddress("MyUsername")).thenReturn(getDummyAddress());

        AddressResponse response = requester.getWithToken(
                "/delivery/address/default", "MyToken", AddressResponse.class).getBody();

        assert response != null;
        assertEquals(getDummyAddress(), response.toAddress());
    }
}
