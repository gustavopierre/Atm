package com.zinkwork.Atm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zinkwork.Atm.model.AtmMachine;
import com.zinkwork.Atm.service.AtmMachineService;

@RestController
@RequestMapping(value = "/atm")
public class AtmMachineController {
	
	@Autowired
	private AtmMachineService service;
	
    @GetMapping(path = "/atms")
	public ResponseEntity<List<AtmMachine>> findAll(){
		List<AtmMachine> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
    @GetMapping(value = "/atms/{id}")
	public ResponseEntity<AtmMachine> findById(@PathVariable Long id){
		AtmMachine obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
