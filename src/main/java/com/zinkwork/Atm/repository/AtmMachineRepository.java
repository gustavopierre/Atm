package com.zinkwork.Atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zinkwork.Atm.model.AtmMachine;

public interface AtmMachineRepository extends JpaRepository<AtmMachine, Long>{
	
}
