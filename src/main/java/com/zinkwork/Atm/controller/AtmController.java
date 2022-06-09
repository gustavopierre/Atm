package com.zinkwork.Atm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zinkwork.Atm.model.Account;

@RestController
@RequestMapping(value = "/atm")
public class AtmController {
    @GetMapping(path = "/accounts")
	public ResponseEntity<Account> findAll(){
		Account acc = new Account(1L, 123456789L, 1234L, 800.00, 200.00);
		return ResponseEntity.ok().body(acc);
	}
}
