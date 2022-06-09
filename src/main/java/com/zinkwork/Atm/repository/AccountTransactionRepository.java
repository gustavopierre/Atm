package com.zinkwork.Atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zinkwork.Atm.model.AccountTransaction;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long>{
	
}
