package com.br.qualiti.banck.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
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
	
	/*
	 * Inicialmente é necessário encontrar o registro a ser atualizado na base de dados. 
	 * Se o registro for encontrado, faz-se as atualizações necessárias e assim chama-se o método save 
	 * e retornar os dados do registro atualizados. 
	 * Note que o método save também foi utilizado na criação do registro. 
	 * Caso o objeto tenha sido recuperado da base tenha um ID, será realizado um update e não um insert na tabela.
	 * Caso o registro não seja encontrado, será lançada uma exceção que é retorá um erro HTTP 404 (not found).
	 */
	/*
	 * Um ponto importante para esse método (e também para o processo de criação de registros) 
	 * é que a própria classe de entidade JPA está sendo utilizada como objeto do tipo parâmetro. 
	 * Não é uma prática recomendada utilizar a entidade JPA como um objeto de transferência (ou DTO: Data Transfer Object). 
	 * É sempre bom evitar expor o modelo da base diretamente para o cliente da API. 
	 * Para esse caso, pode-se criar uma classe com todos os atributos da classe Customer, 
	 * exceto o atributo id (ou uma classe com atributos que facilite a manipulação dos dados por um front-end por exemplo).
	 */
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

	public void delete(long id) {
		try {
			customerRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
		}
		
	}

}
