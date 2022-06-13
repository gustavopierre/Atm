package com.zinkwork.Atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zinkwork.Atm.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
}
