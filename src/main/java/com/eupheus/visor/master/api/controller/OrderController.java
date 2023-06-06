package com.eupheus.visor.master.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eupheus.visor.master.api.dto.TransactionalRequest;
import com.eupheus.visor.master.api.dto.TransactionalResponse;
import com.eupheus.visor.master.api.services.OrderService;

@RestController
@RefreshScope
@RequestMapping("/masters")
public class OrderController {
	private @Autowired OrderService orderService;

	@PostMapping("/bookorders")
	public TransactionalResponse bookOrder(@RequestBody TransactionalRequest transactionalRequest) {
		System.out.println("transactionalRequest--"+transactionalRequest.getOrder());
		System.out.println("transactionalRequest--"+transactionalRequest.getPayments());
		return orderService.saveOrder(transactionalRequest);
	}

}

