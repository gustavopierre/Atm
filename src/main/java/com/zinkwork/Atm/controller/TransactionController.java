package com.zinkwork.Atm.controller;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zinkwork.Atm.model.Account;
import com.zinkwork.Atm.model.AtmMachine;
import com.zinkwork.Atm.model.Transaction;
import com.zinkwork.Atm.service.AccountService;
import com.zinkwork.Atm.service.AtmMachineService;
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
    @GetMapping(value = "/balance/{id}")
	public ResponseEntity<Transaction> findById(@PathVariable Long id){
		Transaction obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
    
    /*@PostMapping(value = "/transactions")
    public ResponseEntity<Transaction> insert(@RequestBody Transaction obj){
    	obj.setMomment(Instant.now());
    	obj = service.insert(obj);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/transaction/{id}").buildAndExpand(obj.getId()).toUri();
    	return ResponseEntity.created(uri).body(obj);
    }
    */
    
    
	@PostMapping(value = "/transactions")
    public ResponseEntity<Transaction> insert(@RequestParam Long atm_id, @RequestParam Long account_id, @RequestParam Double value){
    	Transaction transaction = new Transaction();
    	
    	transaction.setMomment(Instant.now());
    	transaction.setValue(value);
    	
    	transaction = service.insert(transaction, atm_id, account_id);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/transaction/{id}").buildAndExpand(transaction.getId()).toUri();
    	return ResponseEntity.created(uri).body(transaction);
    }
    
    /*TEST ===============
    @GetMapping("/test")
    @ResponseBody
    public String getFoos(@RequestParam Long atm_id, @RequestParam Long account_id, @RequestParam Double value) {
    	atm_id++;
    	account_id++;
    	value += 10.0;
        return "ID_ATM: " + atm_id + "\nID_ACCOUNT " + account_id + "\nVALUE: $" + value;
    }
    @PostMapping("/test")
    @ResponseBody
    public String getFoos2(@RequestParam Long atm_id, @RequestParam Long account_id, @RequestParam Double value) {
    	atm_id++;
    	account_id++;
    	value += 10.0;
        return "ID_ATM: " + atm_id + "\nID_ACCOUNT " + account_id + "\nVALUE: $" + value;
    }
    */
}
