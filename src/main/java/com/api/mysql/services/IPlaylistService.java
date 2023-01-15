package com.api.mysql.services;

import java.util.List;

import com.api.mysql.entities.PlaylistEntity;

public interface IPlaylistService {
	
	PlaylistEntity createPlaylist(PlaylistEntity playlist);
	
	PlaylistEntity updatePlaylist(PlaylistEntity playlist);
	
	List<PlaylistEntity> getAllPlaylists();
	
	PlaylistEntity getPlaylistById(int idplaylist);
	
	void deletePlaylist(int idplaylist);

}
