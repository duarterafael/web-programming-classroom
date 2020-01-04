package com.br.qualiti.banck.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.qualiti.banck.dto.DepositDTO;
import com.br.qualiti.banck.exception.ResourceNotFoundException;
import com.br.qualiti.banck.model.Account;
import com.br.qualiti.banck.service.OperationService;

@RestController()
@RequestMapping("/api/v1/operation")
public class OperationController {

	private OperationService operationService;
	
	OperationController(OperationService operationService) {
		this.operationService = operationService;
	}
	
	@PostMapping(value="/deposit")
	public ResponseEntity deposit(@RequestBody DepositDTO depositDTO) {
		try {
			Account account = operationService.deposit(depositDTO);
			return ResponseEntity.ok().body(account);
		}catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
