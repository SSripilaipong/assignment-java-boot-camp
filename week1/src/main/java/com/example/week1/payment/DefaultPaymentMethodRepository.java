package com.example.week1.payment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DefaultPaymentMethodRepository extends JpaRepository<DefaultPaymentMethod, String> {
}
