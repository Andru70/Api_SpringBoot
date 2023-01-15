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

import com.api.mysql.entities.PodcastEntity;
import com.api.mysql.services.IPodcastService;

@RestController
public class PodcastController {
	
	@Autowired
	private IPodcastService services;
	
	@GetMapping("/podcasts")
	public List<PodcastEntity> getAllPodcasts(){
		return services.getAllPodcasts();
	}
	
	@GetMapping("/podcast/{id}")
	public ResponseEntity<PodcastEntity> getPodcast(@PathVariable Integer id){
		return ResponseEntity.ok().body(services.getPodcastById(id));
	}
	
	@PostMapping("/newpodcast")
	public ResponseEntity<PodcastEntity> createPodcast(@RequestBody PodcastEntity Pod){
		return ResponseEntity.ok().body(services.createPodcast(Pod));
	}
	
	@DeleteMapping("/podcast/{id}")
	public HttpStatus deletePodcast(@PathVariable int id) {
		services.deletePodcast(id);
		return HttpStatus.OK;
	}
	
	@PutMapping("/podcast/update")
	public ResponseEntity<PodcastEntity> updatePodcast(@RequestBody PodcastEntity Pod) {
		return ResponseEntity.ok().body(services.updatePodcast(Pod));
		
	}
	
	
}
