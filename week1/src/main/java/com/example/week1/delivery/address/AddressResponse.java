package com.example.week1.delivery.address;

import java.util.Objects;

public class AddressResponse {

    private String fullName;
    private String address;
    private String postCode;
    private String district;
    private String province;
    private String phone;

    public AddressResponse() {
    }

    public AddressResponse(
            String fullName, String address, String postCode, String district, String province, String phone) {
        this.fullName = fullName;
        this.address = address;
        this.postCode = postCode;
        this.district = district;
        this.province = province;
        this.phone = phone;
    }

    public static AddressResponse fromAddress(Address address) {
        return new AddressResponse(address.getFullName(), address.getAddress(), address.getPostCode(),
                address.getDistrict(), address.getProvince(), address.getPhone());
    }

    public Address toAddress() {
        return new Address(getFullName(), getAddress(), getPostCode(), getDistrict(), getProvince(), getPhone());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressResponse that = (AddressResponse) o;
        return Objects.equals(fullName, that.fullName) && Objects.equals(address, that.address) && Objects.equals(postCode, that.postCode) && Objects.equals(district, that.district) && Objects.equals(province, that.province) && Objects.equals(phone, that.phone);
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
