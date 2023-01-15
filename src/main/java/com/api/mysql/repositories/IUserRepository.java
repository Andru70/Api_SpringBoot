package com.api.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mysql.entities.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long>{

}
