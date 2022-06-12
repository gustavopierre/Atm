package com.zinkwork.Atm.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.zinkwork.Atm.model.Account;
import com.zinkwork.Atm.model.AtmMachine;
import com.zinkwork.Atm.model.Transaction;
import com.zinkwork.Atm.service.AtmService;

@RestController
@RequestMapping(value = "/atm")
public class AtmController {

	@Autowired
	private AtmService service;
	
    @GetMapping(path = "/accounts")
	public ResponseEntity<List<Account>> findAllAccount(){
		List<Account> list = service.findAllAccount();
		return ResponseEntity.ok().body(list);
	}
    
    @GetMapping(value = "/accounts/{id}")
	public ResponseEntity<Account> findByIdAccount(@PathVariable Long id){
		Account obj = service.findByIdAccount(id);
		return ResponseEntity.ok().body(obj);
	}
	
    @GetMapping(value = "/balance")
	public ResponseEntity<Account> balanceAccount(@RequestParam Long account_id, @RequestParam long pin){
		Account obj = service.findByIdAccount(account_id);
		if (service.checkPin(obj, pin)) {
			return ResponseEntity.ok().body(obj); 
		}
		return ResponseEntity.badRequest().body(null);
	}
        
    @PostMapping(value = "/accounts")
    public ResponseEntity<Account> insertAccount(@RequestBody Account obj){
    	obj = service.insertAccount(obj);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/accounts/{id}").buildAndExpand(obj.getId()).toUri();
    	return ResponseEntity.created(uri).body(obj);
    }
    
    @DeleteMapping(value = "/accounts/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id){
    	service.deleteAccount(id);
    	return ResponseEntity.noContent().build();
    }
    
    @PutMapping(value = "/accounts/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account obj){
    	obj = service.updateAccount(id, obj);
    	return ResponseEntity.ok().body(obj);
    }
    
    @GetMapping(path = "/transactions")
	public ResponseEntity<List<Transaction>> findAllTransaction(){
		List<Transaction> list = service.findAllTransaction();
		return ResponseEntity.ok().body(list);
	}
    @GetMapping(value = "/transactions/{id}")
	public ResponseEntity<Transaction> findByIdTransaction(@PathVariable Long id){
		Transaction obj = service.findByIdTransaction(id);
		return ResponseEntity.ok().body(obj);
	}
    
    @PostMapping(value = "/withdraw")
    @ResponseBody
    public ResponseEntity<String> insertTransaction(@RequestParam Long atm_id, @RequestParam Long account_id, @RequestParam Double value, @RequestParam Long pin){
    	String message = service.insertTransaction(atm_id, account_id, value, pin);
    	//URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/transaction/{id}").buildAndExpand(obj.getId()).toUri();
    	return new ResponseEntity<>(message, HttpStatus.OK);
    }
    
    @GetMapping(path = "/atms")
	public ResponseEntity<List<AtmMachine>> findAllAtmMachine(){
		List<AtmMachine> list = service.findAllAtmMachine();
		return ResponseEntity.ok().body(list);
	}
    
    @GetMapping(value = "/atms/{id}")
	public ResponseEntity<AtmMachine> findByIdAtmMachine(@PathVariable Long id){
		AtmMachine obj = service.findByIdAtmMachine(id);
		return ResponseEntity.ok().body(obj);
	}
    
    @PostMapping(value = "/atms")
    public ResponseEntity<AtmMachine> insertAtmMAchine(@RequestBody AtmMachine obj){
    	obj = service.insertAtmMachine(obj);
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/atms/{id}").buildAndExpand(obj.getId()).toUri();
    	return ResponseEntity.created(uri).body(obj);
    }
    
    @DeleteMapping(value = "/atms/{id}")
    public ResponseEntity<Void> deleteAtmMAchine(@PathVariable Long id){
    	service.deleteAtmMachine(id);
    	return ResponseEntity.noContent().build();
    }
    
    @PutMapping(value = "/atms/{id}")
    public ResponseEntity<AtmMachine> updateAtmMAchine(@PathVariable Long id, @RequestBody AtmMachine obj){
    	obj = service.updateAtmMachine(id, obj);
    	return ResponseEntity.ok().body(obj);
    }
   
    
    
    
    
}
