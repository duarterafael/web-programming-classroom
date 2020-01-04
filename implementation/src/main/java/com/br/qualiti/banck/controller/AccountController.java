package com.br.qualiti.banck.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.qualiti.banck.model.Account;
import com.br.qualiti.banck.service.AccountService;

@RestController()
@RequestMapping("/api/v1/accounts")
public class AccountController {

	private AccountService accountService;

	AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping
	public List findAll() {
		return accountService.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity findById(@PathVariable long id) {
		return accountService.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	
	@PostMapping
	public Account create(@RequestBody Account account) {
		return accountService.create(account);
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity delete(@PathVariable long id) {
			accountService.delete(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
