package com.api.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mysql.entities.PodcastEntity;

public interface IPodcastRepository extends JpaRepository<PodcastEntity, Integer>{

}
