package com.api.mysql.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.api.mysql.entities.PlaylistEntity;
import com.api.mysql.repositories.IPlaylistRepository;

@Service
public class PlaylistService implements IPlaylistService{
	
	@Autowired
	private IPlaylistRepository playlistRepository;

	@Override
	public PlaylistEntity createPlaylist(PlaylistEntity playlist) {
		return playlistRepository.save(playlist);
	}

	@Override
	public PlaylistEntity updatePlaylist(PlaylistEntity playlist) {
		
		Optional<PlaylistEntity> playlistB = this.playlistRepository.findById(playlist.getPlaylistID());
		
		if(playlistB.isPresent()) {
			
			PlaylistEntity playlistUpdate = playlistB.get();
			
			playlistUpdate.setNombre(playlist.getNombre());
			
			playlistRepository.save(playlistUpdate);
			
			return playlistUpdate;
			
				
		}else {
			throw new ResourceNotFoundException("Playlist con id" + playlist.getPlaylistID() + "no encontrada");	
		}
		
	}

	@Override
	public List<PlaylistEntity> getAllPlaylists() {
		return playlistRepository.findAll();
	}

	@Override
	public PlaylistEntity getPlaylistById(int idplaylist) {
		
		Optional<PlaylistEntity> playlistB = this.playlistRepository.findById(idplaylist);
		
		if(playlistB.isPresent()) {
			return playlistB.get();
			
			
		}else {
			throw new ResourceNotFoundException("Playlist con id" + idplaylist + "no encontrada");
			
		}
		
	}

	@Override
	public void deletePlaylist(int idplaylist) {
		
		Optional<PlaylistEntity> playlistB = this.playlistRepository.findById(idplaylist);
		
		 if(playlistB.isPresent()) {
			 this.playlistRepository.delete(playlistB.get());
				
				
		 }else {
			 throw new ResourceNotFoundException("Playlist con id" + idplaylist + "no encontrada");
				
		 }
		
	}

}
