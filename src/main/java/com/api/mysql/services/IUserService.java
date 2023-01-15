package com.api.mysql.services;

import java.util.List;

import com.api.mysql.entities.UserEntity;

public interface IUserService {
	
	UserEntity createUser(UserEntity user);
	
	UserEntity updateUser(UserEntity user);
	
	List<UserEntity> getAllUsers();
	
	UserEntity getUserById(long idUser);
	
	/* UserEntity getAccountIdUser(long idAccount); */
	
	/*
	 * 
	 * //Depronto falla, probemos...
	 * 
	 */
	
	void deleteUser(long idUser);

}
