package com.example.week1.delivery.address;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter @Setter @NoArgsConstructor
public class AddressResponse {

    private Integer id;
    private String fullName;
    private String address;
    private String postCode;
    private String district;
    private String province;
    private String phone;

    public AddressResponse(Integer id, String fullName, String address, String postCode, String district,
                           String province, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.postCode = postCode;
        this.district = district;
        this.province = province;
        this.phone = phone;
    }

    public static AddressResponse fromAddress(Address address) {
        return new AddressResponse(address.getId(), address.getFullName(), address.getAddress(), address.getPostCode(),
                address.getDistrict(), address.getProvince(), address.getPhone());
    }

    public Address toAddress() {
        Address result = new Address(getFullName(), getAddress(), getPostCode(), getDistrict(), getProvince(), getPhone());
        result.setId(id);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressResponse that = (AddressResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(fullName, that.fullName) && Objects.equals(address, that.address) && Objects.equals(postCode, that.postCode) && Objects.equals(district, that.district) && Objects.equals(province, that.province) && Objects.equals(phone, that.phone);
    }

}
