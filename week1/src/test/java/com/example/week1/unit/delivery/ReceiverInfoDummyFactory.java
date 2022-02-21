package com.example.week1.unit.delivery;

import com.example.week1.delivery.ReceiverInfo;
import com.example.week1.delivery.address.Address;

public class ReceiverInfoDummyFactory {
    public static Address getDummyAddress() {
        return new Address("Name", "Address", "12345", "District",
                "Province", "0123456789");
    }

    public static ReceiverInfo getDummyInfo() {
        return new ReceiverInfo("MyUsername", getDummyAddress());
    }
}
