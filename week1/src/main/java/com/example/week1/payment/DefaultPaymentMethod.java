package com.example.week1.payment;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Objects;

@Entity
public class DefaultPaymentMethod {
    @Id
    private String username;
    @OneToOne(cascade = CascadeType.ALL)
    private PaymentMethod paymentMethod;

    public DefaultPaymentMethod() {
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
