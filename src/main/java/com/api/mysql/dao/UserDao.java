package com.api.mysql.dao;

import com.api.mysql.entities.AccountEntity;
import com.api.mysql.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private IAccountRepository accountRepository;

    public UserDetails findUserByUsername(String username) {
        AccountEntity account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("El " + username + " no se encuentra registrado"));

        return account;
    }

}
