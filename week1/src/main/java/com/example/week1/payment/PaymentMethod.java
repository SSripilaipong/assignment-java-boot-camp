package com.example.week1.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
@Getter @Setter @NoArgsConstructor
public class PaymentMethod {

    @Id
    private Integer id;
    private String owner;
    private String method;
    private String ownerName;
    private String cardNumber;
    private String expire;
    private String cvv;

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

}
