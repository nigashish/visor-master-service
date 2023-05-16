package com.eupheus.visor.master.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accelerate.visor.model.Order;
import com.eupheus.visor.master.api.dto.Payments;
import com.eupheus.visor.master.api.dto.TransactionalRequest;
import com.eupheus.visor.master.api.dto.TransactionalResponse;
import com.eupheus.visor.master.api.repository.OrderRepository;

@Service
public class OrderService {
	private @Autowired OrderRepository orderRepository;
	private @Autowired RestTemplate restTemplate;

	public TransactionalResponse saveOrder(TransactionalRequest transactionalRequest) {
		String responsemsg="";
		Order order = transactionalRequest.getOrder();
		Payments payments = transactionalRequest.getPayments();
		payments.setOrderId(order.getId());
		payments.setAmount(order.getPrice());
		//rest call from here 
		//Payments paymentResponse = restTemplate.postForObject("http://localhost:9192/payments/dopayment", payments, Payments.class);
		Payments paymentResponse = restTemplate.postForObject("http://VISOR-PRS-SERVICE/prs/dopayment", payments, Payments.class);

		responsemsg=paymentResponse.getPaymentStatus().equals("SUCCSESS")?
				"Payment Processing is successful and order Placed":"There is no failure in payment ,itms is added to card";
		orderRepository.save(order);
		 return new TransactionalResponse(order,paymentResponse.getTrantactionId(),paymentResponse.getAmount(),responsemsg);
	}

}
