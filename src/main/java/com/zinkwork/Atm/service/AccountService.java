package com.zinkwork.Atm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zinkwork.Atm.model.Account;
import com.zinkwork.Atm.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository repository;
	
	public List<Account> findAll(){
		return repository.findAll();
	}
	
	public Account findById(Long id){
		Optional<Account> obj = repository.findById(id);
		return obj.get();
	}
	
	public Account insert(Account obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public Account update(Long id, Account obj) {
		Account entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Account entity, Account obj) {
		entity.setPin(obj.getPin());
		entity.setOverdraft((obj.getOverdraft()));
		entity.updateBalance(0.00);		
	}
	
}
