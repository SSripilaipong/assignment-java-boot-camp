package com.example.week1.payment;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class PaymentMethod {

    @Id
    private Integer id;
    private String owner;
    private String method;
    private String ownerName;
    private String cardNumber;
    private String expire;
    private String cvv;

    public PaymentMethod() {
    }

    public PaymentMethod(Integer id, String owner, String method, String ownerName, String cardNumber, String expire, String cvv) {
        this.id = id;
        this.owner = owner;
        this.method = method;
        this.ownerName = ownerName;
        this.cardNumber = cardNumber;
        this.expire = expire;
        this.cvv = cvv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return Objects.equals(owner, that.owner) && Objects.equals(id, that.id) && Objects.equals(method, that.method) && Objects.equals(ownerName, that.ownerName) && Objects.equals(cardNumber, that.cardNumber) && Objects.equals(expire, that.expire) && Objects.equals(cvv, that.cvv);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
