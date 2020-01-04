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
	
//	public Customer update(@PathVariable("id") long id, @RequestBody Account account) {
//		Optional<Account> currentAccount = accountRepository.findById(id);
//		if(currentAccount.isPresent())
//		{
//			currentAccount.get().setName(customer.getName());
//			currentCustomer.get().setCPF(customer.getCPF());
//			currentCustomer.get().getAccounts().addAll(customer.getAccounts());
//			return customerRepository.save(currentCustomer.get());
//		}else
//		{
//			throw new ResourceNotFoundException("Account", "Account", "A conta com id:"+id+" não encontrado");
//		}
//		
//	}

	public void delete(long id) {
		try {
			accountRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
		}
		
	}

}
