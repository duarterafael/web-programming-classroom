package com.br.qualiti.banck.dto;

public class DepositDTO extends AbstractOperationDTO {

	public DepositDTO() {
		super();
	}

	public DepositDTO(Long customer_id, Long account_id, Double value) {
		super(customer_id, account_id, value);
	}
	
}
