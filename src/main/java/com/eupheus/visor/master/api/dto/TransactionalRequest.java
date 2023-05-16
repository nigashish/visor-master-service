package com.eupheus.visor.master.api.dto;


import com.accelerate.visor.model.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionalRequest {
	private Order order;
	private Payments payments;
	
}
