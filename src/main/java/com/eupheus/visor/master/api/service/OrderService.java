package com.eupheus.visor.master.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.accelerate.visor.school.model.Order;
import com.eupheus.visor.master.api.dto.Payments;
import com.eupheus.visor.master.api.dto.TransactionalRequest;
import com.eupheus.visor.master.api.dto.TransactionalResponse;
import com.eupheus.visor.master.api.repository.OrderRepository;

@Service
@RefreshScope
public class OrderService {
	private @Autowired OrderRepository orderRepository;
	@Autowired
	@Lazy
	private RestTemplate restTemplate;
	
	@Value("${microservice.visor-prs-service.endpoints.endpoint.uri}")
    private String PRS_ENDPOINT_URL;
	@Value("${microservice.visor-master-service.endpoints.endpoint.uri}")
    private String MASTER_ENDPOINT_URL;
	@Value("${microservice.visor-exams-service.endpoints.endpoint.uri}")
    private String EXAMS_ENDPOINT_URL;
	@Value("${microservice.visor-schoolapp-service.endpoints.endpoint.uri}")
    private String SCHOOL_ENDPOINT_URL;
	@Value("${microservice.visor-teacherapp-service.endpoints.endpoint.uri}")
    private String TEACHER_ENDPOINT_URL;
	@Value("${microservice.visor-adminapp-service.endpoints.endpoint.uri}")
    private String ADMIN_ENDPOINT_URL;
	
	/*
	 * @Value("${eureka.client.register-with-eureka}") private String eureka;
	 */


	public TransactionalResponse saveOrder(TransactionalRequest transactionalRequest) {
		String responsemsg = "";
		System.out.println("--PRS_ENDPOINT_URL"+PRS_ENDPOINT_URL);
		Order order = transactionalRequest.getOrder();
		Payments payments = transactionalRequest.getPayments();
		payments.setOrderId(order.getId());
		payments.setAmount(order.getPrice());
		// rest call from here
		// Payments paymentResponse =
		// restTemplate.postForObject("http://localhost:9192/payments/dopayment",
		// payments, Payments.class);
		/*
		 * Payments paymentResponse =
		 * restTemplate.postForObject("http://VISOR-PRS-SERVICE/prs/dopayment",
		 * payments, Payments.class);
		 */
	    Payments paymentResponse = restTemplate.postForObject(PRS_ENDPOINT_URL +
		 "dopayment", payments, Payments.class);
		responsemsg = paymentResponse.getPaymentStatus().equals("SUCCSESS")
				? "Payment Processing is successful and order Placed"
				: "There is no failure in payment ,itms is added to card";
		orderRepository.save(order);
		return new TransactionalResponse(order, paymentResponse.getTrantactionId(), paymentResponse.getAmount(),
				responsemsg);
	}

}