package com.learning.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.api.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer>{

	Payment findByOrderId(int orderId);

}
