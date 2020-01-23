package com.br.qualiti.banck.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.br.qualiti.banck.model.Account;
import com.br.qualiti.banck.repository.AccountRepository;

@Service
public class AccountService {

	private AccountRepository accountRepository;

	AccountService(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	public List findAll() {
		return accountRepository.findAll();
	}
	
	public Optional<Account> findById(long id) {
		return accountRepository.findById(id);
	}

	public Account create(Account account) {
		return accountRepository.save(account);
	}
	

	public void delete(long id) {
		try {
			accountRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
		}
		
	}

}
