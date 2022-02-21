package com.example.week1.delivery.address;

import com.example.week1.delivery.ReceiverInfo;
import com.example.week1.delivery.ReceiverInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private ReceiverInfoRepository receiverInfoRepository;

    public Address getMyDefaultAddress(String username) {
        return receiverInfoRepository.findById(username).map(ReceiverInfo::getAddress).orElse(null);
    }

    public void setReceiverInfoRepository(ReceiverInfoRepository receiverInfoRepository) {
        this.receiverInfoRepository = receiverInfoRepository;
    }

    public void setMyDefaultAddress(String username, Address address) {
        receiverInfoRepository.save(new ReceiverInfo(username, address));
    }

    public boolean isMyAddress(String username, Integer addressId) {
        return false;
    }
}
