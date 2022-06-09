package com.zinkwork.Atm.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zinkwork.Atm.model.Account;
import com.zinkwork.Atm.model.AccountTransaction;
import com.zinkwork.Atm.model.AtmMachine;
import com.zinkwork.Atm.repository.AccountRepository;
import com.zinkwork.Atm.repository.AccountTransactionRepository;
import com.zinkwork.Atm.repository.AtmMachineRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AtmMachineRepository atmMachineRepository;

	@Autowired
	private AccountTransactionRepository accountTransactionRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Account acc1 = new Account(null, 123456789L, 1234L, 800.00, 200.00);
		Account acc2 = new Account(null, 987654321L, 4321L, 1230.00, 150.00);
		
		AtmMachine atm1 = new AtmMachine(null, 10, 30, 30, 20);
		
		atmMachineRepository.saveAll(Arrays.asList(atm1));
		
		accountRepository.saveAll(Arrays.asList(acc1, acc2));
		
		AccountTransaction acctr1 = new AccountTransaction(acc1, atm1, 100.00);
		AccountTransaction acctr2 = new AccountTransaction(acc1, atm1, 150.00);
		AccountTransaction acctr3 = new AccountTransaction(acc2, atm1, 30.00);
		AccountTransaction acctr4 = new AccountTransaction(acc1, atm1, 100.00);
		AccountTransaction acctr5 = new AccountTransaction(acc2, atm1, 120.00);
		AccountTransaction acctr6 = new AccountTransaction(acc1, atm1, 5.00);
		
		
		
		
		accountTransactionRepository.saveAll(Arrays.asList(acctr1, acctr2, acctr3, acctr4, acctr5, acctr6));
		
		
		
	}
	
	
}
