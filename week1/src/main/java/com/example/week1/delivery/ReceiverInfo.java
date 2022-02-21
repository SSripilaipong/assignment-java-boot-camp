package com.example.week1.delivery;

import com.example.week1.delivery.address.Address;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class ReceiverInfo {
    @Id
    private String username;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    public ReceiverInfo() {
    }

    public ReceiverInfo(String username, Address address) {
        this.username = username;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiverInfo that = (ReceiverInfo) o;
        return Objects.equals(username, that.username) && Objects.equals(address, that.address);
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
