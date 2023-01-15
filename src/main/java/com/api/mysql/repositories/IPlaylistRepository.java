package com.api.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mysql.entities.PlaylistEntity;

public interface IPlaylistRepository extends JpaRepository<PlaylistEntity, Integer>{

}
