package com.br.qualiti.banck.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
/*
 * A anotação @Entity pertence ao JPA e isso significa que a classe será automaticamente mapeada à tabela com o mesmo nome 
 * (classe Account e tabela Account).
 * Todos os atributos dessa classe também serão mapeados com as respectivas colunas. 
 * Podemos omitir a anotação @Column para cada atributo da classe desde que o nome do atributo seja o mesmo nome da coluna. 
 * Caso a coluna tenha o nome diferente do atributo precisamos especificar.
 */
public class Account {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
	private String number;

	@Column(nullable = false)
	private Double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Account() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
    
    
}