package com.br.qualiti.banck.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.br.qualiti.banck.model.Customer;
import com.br.qualiti.banck.repository.CustomerRepository;
import java.util.List;

@RestController()
@RequestMapping("/api/v1/customers")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	CustomerController(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@GetMapping
	public List findAll(){
		  List cutomers = customerRepository.findAll();
		  return cutomers;
	}
}
