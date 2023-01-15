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

import com.api.mysql.entities.CancionEntity;
import com.api.mysql.services.ICancionService;

@RestController
public class CancionController {
	
	@Autowired
	private ICancionService services;
	
	@GetMapping("/cancions")
	public List<CancionEntity> getAllCancions(){
		return services.getAllCancions();
	}
	
	@GetMapping("/cancion/{id}")
	public ResponseEntity<CancionEntity> getCancion(@PathVariable Integer id){
		return ResponseEntity.ok().body(services.getCancionById(id));
	}
	
	@PostMapping("/newcancion")
	public ResponseEntity<CancionEntity> createCancion(@RequestBody CancionEntity Can){
		return ResponseEntity.ok().body(services.createCancion(Can));
	}
	
	@DeleteMapping("/cancion/{id}")
	public HttpStatus deletePodcast(@PathVariable int id) {
		services.deleteCancion(id);
		return HttpStatus.OK;
	}
	
	@PutMapping("/cancion/update")
	public ResponseEntity<CancionEntity> updateCancion(@RequestBody CancionEntity Can) {
		return ResponseEntity.ok().body(services.updateCancion(Can));
		
	}

}
