package com.api.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mysql.entities.CancionEntity;

public interface ICancionRepository extends JpaRepository<CancionEntity, Integer>{

}
