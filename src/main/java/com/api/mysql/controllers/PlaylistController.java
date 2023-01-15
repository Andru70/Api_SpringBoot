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

import com.api.mysql.entities.PlaylistEntity;
import com.api.mysql.services.IPlaylistService;

@RestController
public class PlaylistController {
	
	@Autowired
	private IPlaylistService services;
	
	@GetMapping("/playlists")
	public List<PlaylistEntity> getAllPlaylists(){
		return services.getAllPlaylists();
	}
	
	@GetMapping("/playlist/{id}")
	public ResponseEntity<PlaylistEntity> getPlaylist(@PathVariable Integer id){
		return ResponseEntity.ok().body(services.getPlaylistById(id));
	}
	
	@PostMapping("/newplaylist")
	public ResponseEntity<PlaylistEntity> createPlaylist(@RequestBody PlaylistEntity Pli){
		return ResponseEntity.ok().body(services.createPlaylist(Pli));
	}
	
	@DeleteMapping("/playlist/{id}")
	public HttpStatus deletePlaylist(@PathVariable int id) {
		services.deletePlaylist(id);
		return HttpStatus.OK;
	}
	
	@PutMapping("/playlist/update")
	public ResponseEntity<PlaylistEntity> updatePlaylist(@RequestBody PlaylistEntity Pli) {
		return ResponseEntity.ok().body(services.updatePlaylist(Pli));
		
	}

}
