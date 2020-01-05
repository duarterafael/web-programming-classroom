package com.br.qualiti.banck.dto;

public class TransferDTO {
	private Long source_customer_id;
	private Long source_account_id;
	private Long targer_customer_id;
	private Long targer_account_id;
	private Double value;
	
	public TransferDTO() {}
	
	public TransferDTO(Long source_customer_id, Long source_account_id, Long targer_customer_id, Long targer_account_id,
			Double value) {
		super();
		this.source_customer_id = source_customer_id;
		this.source_account_id = source_account_id;
		this.targer_customer_id = targer_customer_id;
		this.targer_account_id = targer_account_id;
		this.value = value;
	}


	public Long getCustomer_id() {
		return source_customer_id;
	}
	public void setCustomer_id(Long customer_id) {
		this.source_customer_id = customer_id;
	}
	public Long getAccount_id() {
		return source_account_id;
	}
	public void setAccount_id(Long account_id) {
		this.source_account_id = account_id;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}

	public Long getSource_customer_id() {
		return source_customer_id;
	}

	public void setSource_customer_id(Long source_customer_id) {
		this.source_customer_id = source_customer_id;
	}

	public Long getSource_account_id() {
		return source_account_id;
	}

	public void setSource_account_id(Long source_account_id) {
		this.source_account_id = source_account_id;
	}

	public Long getTarger_customer_id() {
		return targer_customer_id;
	}

	public void setTarger_customer_id(Long targer_customer_id) {
		this.targer_customer_id = targer_customer_id;
	}

	public Long getTarger_account_id() {
		return targer_account_id;
	}

	public void setTarger_account_id(Long targer_account_id) {
		this.targer_account_id = targer_account_id;
	}
	
}
