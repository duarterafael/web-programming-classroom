package com.br.qualiti.banck.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.qualiti.banck.dto.DepositDTO;
import com.br.qualiti.banck.exception.ResourceNotFoundException;
import com.br.qualiti.banck.model.Account;
import com.br.qualiti.banck.model.Customer;
import com.br.qualiti.banck.repository.AccountRepository;
import com.br.qualiti.banck.repository.CustomerRepository;

@Service
public class OperationService {

	private CustomerRepository customerRepository;
	private AccountRepository accountRepository;
	
	public OperationService(CustomerRepository customerRepository, AccountRepository accountRepository)
	{
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
		
	}

	public Account deposit(DepositDTO depositDTO) {
		
		Optional<Customer> customer = customerRepository.findById(depositDTO.getCustomer_id());
		if(customer.isPresent())
		{
			Account selectAccount = null;
			for (Account account : customer.get().getAccounts()) {
				if(account.getId() == depositDTO.getAccount_id())
				{
					selectAccount = account;
					break;
				}
			}
			
			if(selectAccount == null)
			{
				throw new ResourceNotFoundException("Customer", "Client", "Conta não associado ao cliente");	
			}
			selectAccount.setBalance(selectAccount.getBalance() + depositDTO.getValue());
			return accountRepository.save(selectAccount);
		}else
		{
			throw new ResourceNotFoundException("Customer", "Client", "O cliente com id:"+depositDTO.getCustomer_id()+" não encontrado");
		}
		
	}

}
