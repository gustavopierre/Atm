package com.zinkwork.Atm.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zinkwork.Atm.model.Account;
import com.zinkwork.Atm.model.AtmMachine;
import com.zinkwork.Atm.model.Transaction;
import com.zinkwork.Atm.repository.AccountRepository;
import com.zinkwork.Atm.repository.AtmMachineRepository;
import com.zinkwork.Atm.repository.TransactionRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private AtmMachineRepository atmMachineRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public void run(String... args) throws Exception {

		AtmMachine atm1 = new AtmMachine(null, 10, 30, 30, 20);

		Account acc1 = new Account(null, 123456789L, 1234L, 800.00, 200.00);
		Account acc2 = new Account(null, 987654321L, 4321L, 1230.00, 150.00);

		atmMachineRepository.saveAll(Arrays.asList(atm1));

		accountRepository.saveAll(Arrays.asList(acc1, acc2));

		Transaction t1 = new Transaction(null, 100.00, atm1, acc1);
		Transaction t2 = new Transaction(null, 50.00, atm1, acc1);
		Transaction t3 = new Transaction(null, 200.00, atm1, acc2);
		
		transactionRepository.saveAll(Arrays.asList(t1, t2, t3));
		
		
	}
	
	

}
