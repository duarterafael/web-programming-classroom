package com.br.qualiti.banck.controller;

import org.springframework.data.querydsl.binding.QuerydslPredicate;
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
	
	/*
	 * Para atualizar um registro, é necessário informar seu ID no caminho da URL (similar ao processo de obter um registro específico). 
	 * Caso deseje usar um nome de variável diferente do que foi utilizado também pode utilizar o seguinte código @PathVariable("recordID") long id, 
	 * desde que otherID também seja o nome em @PutMapping(value="/{otherID}"). 
	 * Além do ID, também é necessário passar o objeto com os dados atualizados.
	 */

	@PutMapping(value="/{id}")
	public ResponseEntity update(@PathVariable("id") long id, @RequestBody Customer customer) {
		try {
			Customer updatedCustomer = customerService.update(id, customer);
			return ResponseEntity.ok().body(updatedCustomer);
		}catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	/*
	 * Para remover um contato pelo ID, utiliza-se o id que foi passado como parâmetro.
	 * Será chamado o método delete da classe service e por sia vez, utiliza-se o método deleteById da interface JpaRepository.
	 * É importante salientar que o status HTTP 204 (no content) será retornado independente se tiver ou não registro na tabela.
	 */
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity delete(@PathVariable long id) {
			customerService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	/*
	 * E a API RESTful CRUD está pronta! Um ponto importante é a anotação que foi utilizada em cada método que foi desenvolvido:
	 * Listar todos os contatos - @GetMapping(“/api/v1/customers")
	 * Obter um contato específico pelo ID - @GetMapping(“/api/v1/customers/{id}”)
	 * Remover um contato pelo ID - @DeleteMapping(“/api/v1/customers/{id}”)
	 * Criar um novo contato - @PostMapping(“/api/v1/customers)
	 * Atualizar detalhes de um contato - @PutMapping(“/api/v1/customers/{id}”)
	 * Apesar da URL ser a mesma (/api/v1/customers), o que garante os métodos HTTP diferentes 
	 * são cada uma das anotações usadas em cada método.
	 */
	
	@GetMapping(value="/name/{name}")
	public List findById(@PathVariable("name") String name) {
		return customerService.findByName(name);
	}


}
