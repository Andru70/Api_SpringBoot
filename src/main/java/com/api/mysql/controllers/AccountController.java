package com.api.mysql.controllers;

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
import org.springframework.web.bind.annotation.RestController;

import com.api.mysql.dto.AccountDTO;
import com.api.mysql.entities.AccountEntity;
import com.api.mysql.services.IAccountService;

@RestController
public class AccountController {
	
	@Autowired
	private IAccountService services;
	
	@GetMapping("/accounts")
	public List<AccountEntity> getAllAccounts(){
		return services.getAllAccounts();
	}
	
	@GetMapping("/account/{id}")
	public ResponseEntity<AccountEntity> getAccount(@PathVariable long id){
		return ResponseEntity.ok().body(services.getAccountById(id));
	}
	
	@GetMapping("/accountdetail/{id}")
	public ResponseEntity<AccountDTO> getAccountDetail(@PathVariable long id){
		return ResponseEntity.ok().body(services.getDetailsAcc(id));
	}
	
	@PostMapping("/newaccount")
	public ResponseEntity<AccountEntity> createAccount(@RequestBody AccountEntity Acc){
		return ResponseEntity.ok().body(services.createAccount(Acc));
	}
	
	@DeleteMapping("/account/{id}")
	public HttpStatus deleteAccount(@PathVariable int id) {
		services.deleteAccount(id);
		return HttpStatus.OK;
	}
	
	@PutMapping("/account/update")
	public ResponseEntity<AccountEntity> updateAccount(@RequestBody AccountEntity Acc) {
		return ResponseEntity.ok().body(services.updateAccount(Acc));
		
	}
	
	

}
