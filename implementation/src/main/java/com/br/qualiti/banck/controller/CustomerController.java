package com.br.qualiti.banck.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
/*
 * O Controller é a classe responsável por expor cada URI que estará disponível na API.
 * A anotação @RestController contém as anotações @Controller e @ResponseBody.  
 * A anotação @Controller representa uma classe com endpoints (URIs que serão expostas pela API) e 
 * a classe indica que o valor retornado pelos métodos devem ser vinculados ao corpo da resposta (response body).
 * A anotação @RequestMapping("/api/v1/customers") indica que a URL da API desse controller começa com /api/v1/customers, 
 * isso significa que pode-se acessar usando a URL http://localhost:8080/api/v1/customers (acesso local).
 * O Spring automaticamente fornece a injeção de dependência. 
 * Este exemplo não está usando a anotação @Autowired pois não é mais considerado uma boa prática para injeção de 
 * dependência de atributos obrigatórios. 
 * Desde a versão 4 do Spring a prática recomendada é o uso de injeção de dependência por construtor 
 * (as IDEs mais modernas inclusive apresentam um alerta quando fazemos o uso do @Autowired).
 * 
 */
import org.springframework.web.bind.annotation.*;

import com.br.qualiti.banck.exception.ResourceNotFoundException;
import com.br.qualiti.banck.model.Customer;
import com.br.qualiti.banck.repository.CustomerRepository;
import com.br.qualiti.banck.service.CustomerService;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/customers")
public class CustomerController {

	private CustomerService customerService;

	CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	/*
	 * O método findAll utiliza o método findAll do service que por sua vez chama a
	 * interface JpaRepository que faz um select * from customers. Como esta é uma
	 * API RESTful, pode-se omitir o código @RequestMapping(value="/customer",
	 * method=RequestMethod.GET) e utilizar somente a anotação @GetMapping.
	 */
	@GetMapping
	public List findAll() {
		return customerService.findAll();
	}

	/*
	 * Seguindo os conceitos RESTful, é necessário passar na URL o ID do registro. A
	 * anotação @PathVariable vincula o parâmetro passado pelo método com a variável
	 * do path. Note que o parâmetro long id tem o mesmo nome do path declarado
	 * em @GetMapping(path = {"/{id}"}). A lógica para obter um cliente específico é
	 * utilizar o método findById da interface JpaRepository que encontrase na
	 * camada de serviço (que irá fazer um select * from contacts where id = ?).
	 * Caso o registro seja encontrado, é retornado o status HTTP 200
	 * (ResponseEntity.ok()) e no corpo da resposta é enviado o registro. Caso o
	 * registro não seja encontrado é retornado o status HTTP 404 - Não Encontrado
	 * (ResponseEntity.notFound()). Existe também a diferença no tipo do retorno dos
	 * métodos. Enquanto to findAll retorna uma lista diretamente, o método findById
	 * retorna um ResponseEntity para indicar sucesso ou não.
	 */
	@GetMapping(path = { "/{id}" })
	public ResponseEntity findById(@PathVariable long id) {
		return customerService.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	/*
	 * O método create chama o método create da camada de serviço que por sua vez
	 * chama o save da interface JpaRepository. Após criar o registro na tabela,
	 * retorna o contato com o atributo id populado e o registro é retornado no
	 * corpo de resposta. A anotação @RequestBody indica que o parâmetro contact
	 * será vinculado do corpo da requisição. Isso significa que o método espera o
	 * seguinte conteúdo do corpo da requisição (em formato JSON) Com o uso dessa
	 * anotação, o Spring é inteligente e consegue ler e transformar o conteúdo em
	 * uma instância da classe Customer.
	 */
	@PostMapping
	public Customer create(@RequestBody Customer customer) {
		return customerService.create(customer);
	}

	@PutMapping(value="/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody Customer customer) {
		try {
			Customer updatedCustomer = customerService.update(id, customer);
			return ResponseEntity.ok().body(updatedCustomer);
		}catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	//https://www.oracle.com/technetwork/pt/articles/dsl/crud-rest-sb2-hibernate-5302424-ptb.html

}
