package com.learning.api.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.api.entity.Payment;
import com.learning.api.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payment doPayment(Payment payment) {
		payment.setTransactionId(UUID.randomUUID().toString());
		payment.setPaymentStatus(paymentProcessing());
		return paymentRepository.save(payment);
	}
	
	public String paymentProcessing() {
		return new Random().nextBoolean()?"success":"false";
	}

	public Payment paymentHistoryByOrderId(int orderId) {
		return paymentRepository.findByOrderId(orderId);
	}
}
