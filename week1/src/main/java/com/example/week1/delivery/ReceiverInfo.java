package com.example.week1.delivery;

import com.example.week1.delivery.address.Address;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReceiverInfo {
    @Id
    private String username;

    public ReceiverInfo() {
    }

    public ReceiverInfo(String username, Address address) {
    }
}
