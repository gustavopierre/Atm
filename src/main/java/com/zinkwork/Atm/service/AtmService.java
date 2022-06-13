package com.zinkwork.Atm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.zinkwork.Atm.exception.DatabaseException;
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
		try {
			accountRepository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e){
			throw new ResourceNotFoundException(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
		
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
	
	public String insertTransaction(Long atm_id, Long account_id, Double value, Long pin) {
		Account account = findByIdAccount(account_id);
		AtmMachine atmMachine = findByIdAtmMachine(atm_id);
		Transaction obj = null;
		String message;
		StringBuilder sb = new StringBuilder();
		
		if(checkPin(account, pin)) {
			if (atmMachine.checkValueATM(value)) {
				if (Withdraw.checkWithdraw(account, value)) {
					Withdraw.updateValue(account, value);
					int notes[][] = atmMachine.quantityNotes(value);
					atmMachine.updateQuantityNote50((-1)*notes[0][1]);
					atmMachine.updateQuantityNote20((-1)*notes[1][1]);
					atmMachine.updateQuantityNote10((-1)*notes[2][1]);
					atmMachine.updateQuantityNote5((-1)*notes[3][1]);
					obj = new Transaction(null, value, atmMachine,  account);
					
					transactionRepository.save(obj);
					
					sb.append("Balance:\n");
					sb.append("--------------------------" + "\n");
					sb.append("Account number: " + account.getNumber() + "\n");
					sb.append("--------------------------" + "\n");
					sb.append("Withdraw: €" + String.format("%.2f", value) + "\n");
					sb.append("Notes of €50: " + notes[0][1] + "\n");
					sb.append("Notes of €20: " + notes[1][1] + "\n");
					sb.append("Notes of €10: " + notes[2][1] + "\n");
					sb.append("Notes of €5: " + notes[3][1] + "\n");
					sb.append("--------------------------" + "\n");
					sb.append("Balance: " + String.format("%.2f", account.getBalance()) + "\n");
					sb.append("Overdraft: " + String.format("%.2f", account.getOverdraft()) + "\n");
					sb.append("Withdrw availabe: " + String.format("%.2f", account.getWithdrawAvailable()) + "\n");
					sb.append("--------------------------" + "\n");
					message = sb.toString();
				}
				else {
					message = "Sorry. Your account balance is not enough.";
				}
			}
			else {
				message = "Sorry. The money in this ATM is not enough.";
			}
		}
		else {
			message = "Sorry. Wrong PIN.";
		}
		return message;

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
	
	public boolean checkPin(Account account, long pin) {
		if (pin == account.getPin().longValue()) {
			return true;
		}
		return false;
	}
	
}
