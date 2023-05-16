package com.eupheus.visor.master.api.dto;
import com.accelerate.visor.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionalResponse {
	private Order order;
	private String transactionId;
	private Double amount;
	private String message;
	
}
