package com.zinkwork.Atm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zinkwork.Atm.exception.ResourceNotFoundException;
import com.zinkwork.Atm.model.Account;
import com.zinkwork.Atm.model.AtmMachine;
import com.zinkwork.Atm.model.Transaction;
import com.zinkwork.Atm.repository.AccountRepository;
import com.zinkwork.Atm.repository.AtmMachineRepository;
import com.zinkwork.Atm.repository.TransactionRepository;

@Service
public class AtmService {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private AtmMachineRepository atmMachineRepository;
	
	public List<Account> findAllAccount(){
		return accountRepository.findAll();
	}
	
	public Account findByIdAccount(Long id){
		Optional<Account> obj = accountRepository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Account insertAccount(Account obj) {
		return accountRepository.save(obj);
	}
	
	public void deleteAccount(Long id) {
		accountRepository.deleteById(id);
	}
	
	public Account updateAccount(Long id, Account obj) {
		Account entity = accountRepository.getOne(id);
		updateDataAccount(entity, obj);
		return accountRepository.save(entity);
	}

	public void updateDataAccount(Account entity, Account obj) {
		entity.setPin(obj.getPin());
		entity.setOverdraft((obj.getOverdraft()));
		entity.updateBalance(0.00);		
	}
	
	public List<Transaction> findAllTransaction() {
		return transactionRepository.findAll();
	}

	public Transaction findByIdTransaction(Long id) {
		Optional<Transaction> obj = transactionRepository.findById(id);
		return obj.get();
	}
	
	public Transaction insertTransaction(Long atm_id, Long account_id, Double value, Long pin) {
		Account account = findByIdAccount(account_id);
		AtmMachine atmMachine = findByIdAtmMachine(atm_id);
		Transaction obj = null;
		
		if (atmMachine.checkValueATM(value)) {
			obj = new Transaction(null, value, atmMachine,  account);
	
			return transactionRepository.save(obj);
		}
		return obj;

	}
	
	public List<AtmMachine> findAllAtmMachine() {
		return atmMachineRepository.findAll();
	}

	public AtmMachine findByIdAtmMachine(Long id) {
		Optional<AtmMachine> obj = atmMachineRepository.findById(id);
		return obj.get();
	}

	public AtmMachine insertAtmMachine(AtmMachine obj) {
		return atmMachineRepository.save(obj);
	}

	public void deleteAtmMachine(Long id) {
		atmMachineRepository.deleteById(id);
	}
	
	public AtmMachine updateAtmMachine(Long id, AtmMachine obj) {
		AtmMachine entity = atmMachineRepository.getOne(id);
		updateDataAtmMachine(entity, obj);
		return atmMachineRepository.save(entity);
	}

	public void updateDataAtmMachine(AtmMachine entity, AtmMachine obj) {
		entity.setQuantityNote50(obj.getQuantityNote50());
		entity.setQuantityNote20(obj.getQuantityNote20());
		entity.setQuantityNote10(obj.getQuantityNote10());
		entity.setQuantityNote5(obj.getQuantityNote5());
	}
	
	public boolean checkPin(Account account, Long pin) {
		if (pin == account.getPin()) {
			return true;
		}
		return false;
	}
	
}
