package com.example.week1.delivery.address;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
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

    public Address() {
    }

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
        return Objects.equals(fullName, address1.fullName) && Objects.equals(address, address1.address) && Objects.equals(postCode, address1.postCode) && Objects.equals(district, address1.district) && Objects.equals(province, address1.province) && Objects.equals(phone, address1.phone);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
