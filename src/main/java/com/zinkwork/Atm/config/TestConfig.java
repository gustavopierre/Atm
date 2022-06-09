package com.zinkwork.Atm.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zinkwork.Atm.model.Account;
import com.zinkwork.Atm.model.AtmMachine;
import com.zinkwork.Atm.repository.AccountRepository;
import com.zinkwork.Atm.repository.AtmMachineRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AtmMachineRepository atmMachineRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Account account1 = new Account(null, 123456789L, 1234L, 800.00, 200.00);
		Account account2 = new Account(null, 987654321L, 4321L, 1230.00, 150.00);
		
		AtmMachine atm1 = new AtmMachine(null, 10, 30, 30, 20);
		
		atmMachineRepository.saveAll(Arrays.asList(atm1));
		
		accountRepository.saveAll(Arrays.asList(account1, account2));
		
	}
	
	
}
