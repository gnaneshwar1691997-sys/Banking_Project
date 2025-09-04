package com.nt.Controller;

//import java.security.Provider.Service;
import java.util.List;
import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.Entity.Accounts;
import com.nt.Service.AccountService;

@RestController
public class Controller {
	@Autowired
	public AccountService service;
	

	@GetMapping("/all")
	public List<Accounts> getAllAccounts() {
		return service.getAccounts();

	}

	@PostMapping("/insert")
	public ResponseEntity<?> setAccount(@RequestBody Accounts account) {
		try {
			Accounts saved = service.setAccount(account);
			return ResponseEntity.ok(saved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateAccount(@PathVariable int id, @RequestBody Accounts updatedAccount) {
		try {
			Accounts saved = service.updateAccount(id, updatedAccount);
			return ResponseEntity.ok(saved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@PutMapping("/updateBalace/{id}")
	public ResponseEntity<?> updateAccountBalance(@PathVariable int id, @RequestBody Accounts updatedAccount) {
		try {
			Accounts saved = service.updateAccountBalance(id, updatedAccount);
			return ResponseEntity.ok(saved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
	
	@GetMapping("/status/{id}")
	public ResponseEntity<?> accountStatus(@PathVariable int id) {
	    try {
	        String status = service.accountStatus(id);
	        return ResponseEntity.ok("Account status: " + status);
	    } catch (IllegalArgumentException e) {
	        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
	    }
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteAccount(@PathVariable int id) {
		try {
			Accounts saved = service.deleteAccount(id);
			return ResponseEntity.ok(saved);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
