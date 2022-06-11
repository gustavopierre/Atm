package com.zinkwork.Atm.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zinkwork.Atm.model.Account;
import com.zinkwork.Atm.model.AtmMachine;
import com.zinkwork.Atm.model.Transaction;
import com.zinkwork.Atm.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repository;

	public List<Transaction> findAll() {
		return repository.findAll();
	}

	public Transaction findById(Long id) {
		Optional<Transaction> obj = repository.findById(id);
		return obj.get();
	}
	
	public Transaction insert(Transaction obj,Long account_id, Long atm_id) {
		Optional<Account> account;
    	AccountService accountService = new AccountService();
    	account = accountService.getRepository().findById(account_id);
    	
    	Optional<AtmMachine> atmMachine;
    	AtmMachineService atmMachineService = new AtmMachineService();
    	atmMachine = atmMachineService.getRepository().findById(atm_id);
    	
    	
    	
    	obj.setAccount(account.get());
    	obj.setAtmMachine(atmMachine.get());
		return repository.save(obj);
	}
}
