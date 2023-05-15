package com.eupheus.visor.master.api.dto;

import lombok.Data;

@Data
public class Payments {
	private Long paymentId;
	private String PaymentStatus;
	private String trantactionId;
	private Long orderId;
	private Double amount;

}
