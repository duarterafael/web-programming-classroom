package com.br.qualiti.banck.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
/*
 * Uma classe Service que evoca o repositório e contém a lógica de negócio do projeto para deixar o código da classe controller enxuto e mais limpo, 
 * é necessário declarar o repositório como atributo.
 */
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.qualiti.banck.exception.ResourceNotFoundException;
import com.br.qualiti.banck.model.Customer;
import com.br.qualiti.banck.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;

	CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List findAll() {
		return customerRepository.findAll();
	}
	
	/*
	 * Um ponto importante é notar a diferença entre os métodos findAll e findById da interface JpaRepository.
	 * O método findAll retorna uma lista de entidades. 
	 * Caso não exista nenhum registro na tabela, é retornada uma lista vazia ([]). 
	 * Já o método findById retorna um Optional<T>. 
	 * O classe Optional existe desde o Java 8 e representa um container que pode ou não conter um valor não nulo (diferente de null). 
	 * Essa classe é bem interessante para evitar exceções do tipo NullPointerException,  
	 * Com o retorno do método do método findAll da interface JpaRepository podemos fazer o uso do método map caso o valor retornado seja diferente de nulo. 
	 * O método map transforma (mapeia) o registro retornado em um tipo ResponseEntity.
	 */

	public Optional<Customer> findById(long id) {
		return customerRepository.findById(id);
	}

	public Customer create(Customer customer) {
		return customerRepository.save(customer);
	}
	
	public Customer update(@PathVariable("id") long id, @RequestBody Customer customer) {
		Optional<Customer> currentCustomer = customerRepository.findById(id);
		if(currentCustomer.isPresent())
		{
			currentCustomer.get().setName(customer.getName());
			currentCustomer.get().setCPF(customer.getCPF());
			currentCustomer.get().getAccounts().addAll(customer.getAccounts());
			return customerRepository.save(currentCustomer.get());
		}else
		{
			throw new ResourceNotFoundException("Customer", "Client", "O cliente com id:"+id+" não encontrado");
		}
		
	}

}
