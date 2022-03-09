package com.example.week1.payment;

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
public class DefaultPaymentMethod {
    @Id
    private String username;
    @OneToOne(cascade = CascadeType.ALL)
    private PaymentMethod paymentMethod;

    public DefaultPaymentMethod(String username, PaymentMethod paymentMethod) {
        this.username = username;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultPaymentMethod that = (DefaultPaymentMethod) o;
        return Objects.equals(username, that.username) && Objects.equals(paymentMethod, that.paymentMethod);
    }

}
