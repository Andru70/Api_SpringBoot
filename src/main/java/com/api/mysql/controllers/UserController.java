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

import com.api.mysql.entities.UserEntity;
import com.api.mysql.services.IUserService;

@RestController
public class UserController {
	
	@Autowired
	private IUserService services;
	
	@GetMapping("/users")
	public List<UserEntity> getAllUsers(){
		return services.getAllUsers();
	}
	
	@GetMapping("/user/{idUser}")
	public ResponseEntity<UserEntity> getUser(@PathVariable long idUser){
		return ResponseEntity.ok().body(services.getUserById(idUser));
	}
	
	@PostMapping("/newuser")
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity User){
		return ResponseEntity.ok().body(services.createUser(User));
	}
	
	@DeleteMapping("/user/{id}")
	public HttpStatus deleteUser(@PathVariable int id) {
		services.deleteUser(id);
		return HttpStatus.OK;
	}
	
	@PutMapping("/user/update")
	public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) {
		return ResponseEntity.ok().body(services.updateUser(user));
		
	}

}
