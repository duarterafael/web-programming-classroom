package com.br.qualiti.banck.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.br.qualiti.banck.dto.AbstractOperationDTO;
import com.br.qualiti.banck.dto.DepositDTO;
import com.br.qualiti.banck.dto.WithdrawDTO;
import com.br.qualiti.banck.exception.ResourceNotFoundException;
import com.br.qualiti.banck.model.Account;
import com.br.qualiti.banck.model.Customer;
import com.br.qualiti.banck.repository.AccountRepository;
import com.br.qualiti.banck.repository.CustomerRepository;

@Service
public class OperationService {
	
	public enum OperationType
	{
		Deposit,
		Withdraw,
		Transfer
	}

	private CustomerRepository customerRepository;
	private AccountRepository accountRepository;
	
	public OperationService(CustomerRepository customerRepository, AccountRepository accountRepository)
	{
		this.customerRepository = customerRepository;
		this.accountRepository = accountRepository;
		
	}
	
	private Account AccountOperation(OperationType operationType, AbstractOperationDTO operationDto)
	{
		Optional<Customer> customer = customerRepository.findById(operationDto.getCustomer_id());
		if(customer.isPresent())
		{
			Account selectAccount = null;
			for (Account account : customer.get().getAccounts()) {
				if(account.getId() == operationDto.getAccount_id())
				{
					selectAccount = account;
					break;
				}
			}
			
			if(selectAccount == null)
			{
				throw new ResourceNotFoundException("Customer", "Client", "Conta não associado ao cliente");	
			}
			if(operationType == OperationType.Deposit)
			{
				selectAccount.setBalance(selectAccount.getBalance() + operationDto.getValue());
			}else if(operationType == OperationType.Withdraw)
			{
				selectAccount.setBalance(selectAccount.getBalance() - operationDto.getValue());
			}
			return accountRepository.save(selectAccount);
		}else
		{
			throw new ResourceNotFoundException("Customer", "Client", "O cliente com id:"+operationDto.getCustomer_id()+" não encontrado");
		}
	}

	public Account deposit(DepositDTO depositDTO) {
		return AccountOperation(OperationType.Deposit, depositDTO);
	}

	public Account withdraw(WithdrawDTO withdrawDTO) {
		return AccountOperation(OperationType.Withdraw, withdrawDTO);
	}
	
}

