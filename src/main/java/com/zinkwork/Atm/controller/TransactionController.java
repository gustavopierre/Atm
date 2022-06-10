package com.zinkwork.Atm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zinkwork.Atm.model.Transaction;
import com.zinkwork.Atm.service.TransactionService;

@RestController
@RequestMapping(value = "/atm")
public class TransactionController {
	
	@Autowired
	private TransactionService service;
	
    @GetMapping(path = "/transactions")
	public ResponseEntity<List<Transaction>> findAll(){
		List<Transaction> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
    @GetMapping(value = "/transactions/{id}")
	public ResponseEntity<Transaction> findById(@PathVariable Long id){
		Transaction obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
