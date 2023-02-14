package com.api.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mysql.entities.AccountEntity;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<AccountEntity, Long>{

    Optional<AccountEntity> findByUsername(String username);

}
