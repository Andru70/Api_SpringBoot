package com.api.mysql.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.api.mysql.entities.UserEntity;
import com.api.mysql.repositories.IUserRepository;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserEntity createUser(UserEntity user) {
		return userRepository.save(user);
	}

	@Override
	public UserEntity updateUser(UserEntity user) {
		
		Optional<UserEntity> userB = this.userRepository.findById(user.getUsuarioId());
		
		if(userB.isPresent()) {
			
			UserEntity userUpdate = userB.get();
			
			userUpdate.setUsuarioId(user.getUsuarioId());
			userUpdate.setFullName(user.getFullName());
			
			userRepository.save(userUpdate);
			
			return userUpdate;
			
				
		}else {
			throw new ResourceNotFoundException("User con id" + user.getUsuarioId() + "no encontrado");	
		}

	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity getUserById(long idUser) {
		
		Optional<UserEntity> userB = this.userRepository.findById(idUser);
		
		if(userB.isPresent()) {
			return userB.get();
			
			
		}else {
			throw new ResourceNotFoundException("User con id" + idUser + "no encontrado");
			
		}
	}

	/*
	 * @Override public UserEntity getAccountIdUser(long idAccount) {
	 * 
	 * Optional<UserEntity> userB = this.userRepository.getacc
	 * 
	 * if(userB.isPresent()) { this.userRepository.delete(userB.get());
	 * 
	 * 
	 * }else { throw new ResourceNotFoundException("User con id" + idUser +
	 * "no encontrado");
	 * 
	 * }
	 * 
	 * 
	 * }
	 */
	
	@Override
	public void deleteUser(long idUser) {
		
		Optional<UserEntity> userB = this.userRepository.findById(idUser);
		
		 if(userB.isPresent()) {
			 this.userRepository.delete(userB.get());
				
				
		 }else {
			 throw new ResourceNotFoundException("User con id" + idUser + "no encontrado");
				
		 }
		
	}

	

}
