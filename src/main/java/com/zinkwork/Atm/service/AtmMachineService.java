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
	
	public List<AtmMachine> findAll(){
		return repository.findAll();
	}
	
	public AtmMachine findById(Long id){
		Optional<AtmMachine> obj = repository.findById(id);
		return obj.get();
	}
	
	public AtmMachine insert(AtmMachine obj) {
		return repository.save(obj);
	}
	

}
