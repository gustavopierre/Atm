package com.zinkwork.Atm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zinkwork.Atm.model.Account;
import com.zinkwork.Atm.model.AtmMachine;
import com.zinkwork.Atm.repository.AtmMachineRepository;

@Service
public class AtmMachineService {

	@Autowired
	private AtmMachineRepository repository;

	public List<AtmMachine> findAll() {
		return repository.findAll();
	}

	public AtmMachine findById(Long id) {
		Optional<AtmMachine> obj = repository.findById(id);
		return obj.get();
	}

	public AtmMachine insert(AtmMachine obj) {
		return repository.save(obj);
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public AtmMachine update(Long id, AtmMachine obj) {
		AtmMachine entity = repository.getOne(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(AtmMachine entity, AtmMachine obj) {
		entity.setQuantityNote50(obj.getQuantityNote50());
		entity.setQuantityNote20(obj.getQuantityNote20());
		entity.setQuantityNote10(obj.getQuantityNote10());
		entity.setQuantityNote5(obj.getQuantityNote5());
	}

	
}
