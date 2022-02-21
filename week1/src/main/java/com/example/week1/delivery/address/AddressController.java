package com.example.week1.delivery.address;

import com.example.week1.user.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddressController {

    @Autowired
    private UserTokenManager tokenManager;

    @Autowired
    private AddressService addressService;

    @GetMapping("/delivery/address/default")
    public AddressResponse loadDefaultAddress(@RequestHeader("Authorization") String token) {
        return AddressResponse.fromAddress(
                addressService.getMyDefaultAddress(tokenManager.decodeTokenToUsername(token)));
    }
}
