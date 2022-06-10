package com.zinkwork.Atm.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zinkwork.Atm.model.Account;
import com.zinkwork.Atm.service.AccountService;

@RestController
@RequestMapping(value = "/atm")
public class AccountController {
	
	@Autowired
	private AccountService service;
	
    @GetMapping(path = "/accounts")
	public ResponseEntity<List<Account>> findAll(){
		List<Account> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
    
    @GetMapping(value = "/accounts/{id}")
	public ResponseEntity<Account> findById(@PathVariable Long id){
		Account obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
    
    @PostMapping
    public ResponseEntity<Account> insert(@RequestBody Account obj){
    	obj = service.insert(obj);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/accounts/{id}").buildAndExpand(obj.getId()).toUri();
    	return ResponseEntity.created(uri).body(obj);
    }
}
