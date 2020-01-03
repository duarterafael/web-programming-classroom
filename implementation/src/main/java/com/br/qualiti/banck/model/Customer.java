package com.br.qualiti.banck.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/*
 * A anotação @Entity pertence ao JPA e isso significa que a classe será automaticamente mapeada à tabela com o mesmo nome 
 * (classe Account e tabela Account).
 * Todos os atributos dessa classe também serão mapeados com as respectivas colunas. 
 * Podemos omitir a anotação @Column para cada atributo da classe desde que o nome do atributo seja o mesmo nome da coluna. 
 * Caso a coluna tenha o nome diferente do atributo precisamos especificar.
 */
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    
	@Column(length = 20, nullable = false, unique = true)
	private String CPF;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "customer")
    private List<Account> accounts;

    public Customer() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

    
}
