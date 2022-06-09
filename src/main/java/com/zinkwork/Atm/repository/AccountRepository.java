package com.zinkwork.Atm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zinkwork.Atm.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{
	
}
