package com.example.week1.delivery;

import com.example.week1.delivery.address.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor
public class ReceiverInfo {
    @Id
    private String username;
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

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

}
