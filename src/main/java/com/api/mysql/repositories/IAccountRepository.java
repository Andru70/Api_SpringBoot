package com.api.mysql.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.mysql.entities.AccountEntity;

public interface IAccountRepository extends JpaRepository<AccountEntity, Long>{

}
