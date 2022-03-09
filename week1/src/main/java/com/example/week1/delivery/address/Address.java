package com.example.week1.delivery.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private Integer id;
    private String fullName;
    private String address;
    private String postCode;
    private String district;
    private String province;
    private String phone;

    public Address(String fullName, String address, String postCode, String district, String province, String phone) {
        this.fullName = fullName;
        this.address = address;
        this.postCode = postCode;
        this.district = district;
        this.province = province;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(id, address1.id) && Objects.equals(fullName, address1.fullName) && Objects.equals(address, address1.address) && Objects.equals(postCode, address1.postCode) && Objects.equals(district, address1.district) && Objects.equals(province, address1.province) && Objects.equals(phone, address1.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, address, postCode, district, province, phone);
    }
}
