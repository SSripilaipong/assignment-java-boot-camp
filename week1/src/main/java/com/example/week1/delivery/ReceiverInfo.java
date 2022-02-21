package com.example.week1.delivery;

import com.example.week1.delivery.address.Address;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ReceiverInfo {
    @Id
    private String username;
    @OneToOne
    private Address address;

    public ReceiverInfo() {
    }

    public ReceiverInfo(String username, Address address) {
        this.username = username;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
