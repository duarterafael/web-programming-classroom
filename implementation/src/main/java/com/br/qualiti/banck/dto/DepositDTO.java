package com.br.qualiti.banck.dto;

public class DepositDTO {
	private Long customer_id;
	private Long account_id;
	private Double value;
	
	public DepositDTO() {}
	
	public DepositDTO(Long customer_id, Long account_id, Double value) {
		super();
		this.customer_id = customer_id;
		this.account_id = account_id;
		this.value = value;
	}

	public Long getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}
	public Long getAccount_id() {
		return account_id;
	}
	public void setAccount_id(Long account_id) {
		this.account_id = account_id;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	
	

}
