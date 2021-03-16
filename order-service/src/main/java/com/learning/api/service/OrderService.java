package com.learning.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learning.api.common.Payment;
import com.learning.api.common.TransactionRequest;
import com.learning.api.common.TransactionResponse;
import com.learning.api.entity.Order;
import com.learning.api.repository.OrderRepository;

@Service
@RefreshScope
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	@Lazy
	private RestTemplate template;
	
	@Value("${microservice.payment-service.endpoints.endpoint.uri}")
	private String ENDPOINT_URL;
	
	public TransactionResponse saveOrder(TransactionRequest request) {
		String message = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setAmount(order.getPrice());
		payment.setOrderId(order.getId());
		
		Payment paymentResp = template.postForObject(ENDPOINT_URL, payment, Payment.class);
		
		message =  paymentResp.getPaymentStatus().equals("success")?"payment successsful" : "payment failuer";
		
		repository.save(order);
		return new TransactionResponse(order,paymentResp.getAmount(),paymentResp.getTransactionId(),message);
	}

}
