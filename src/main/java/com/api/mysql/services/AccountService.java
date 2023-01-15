package com.api.mysql.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.api.mysql.dto.AccountDTO;
import com.api.mysql.entities.AccountEntity;
import com.api.mysql.entities.UserEntity;
import com.api.mysql.repositories.IAccountRepository;


@Service
public class AccountService implements IAccountService{
	
	@Autowired
	private IAccountRepository accountRepository;
	
	@Autowired
	private IUserService usRepo;

	@Override
	public AccountEntity createAccount(AccountEntity account) {
		return accountRepository.save(account);
	}

	@Override
	public AccountEntity updateAccount(AccountEntity account) {

		Optional<AccountEntity> accB = this.accountRepository.findById(account.getCuentaID());
		
		if(accB.isPresent()) {
			
			AccountEntity accUpdate = accB.get();
			
			accUpdate.setUserName(account.getUserName());
			accUpdate.setPassword(account.getPassword());
			accUpdate.setType_Account(account.getType_Account());
			accUpdate.setIdUsuario(account.getIdUsuario());
			
			accountRepository.save(accUpdate);
			
			return accUpdate;
			
				
		}else {
			throw new ResourceNotFoundException("Cuenta con id" + account.getCuentaID() + "no encontrada");	
		}
		
	}

	@Override
	public List<AccountEntity> getAllAccounts() {
		return accountRepository.findAll();
	}

	@Override
	public AccountEntity getAccountById(long idAccount) {
		
		Optional<AccountEntity> accB = this.accountRepository.findById(idAccount);
		
		if(accB.isPresent()) {
			return accB.get();
			
			
		}else {
			throw new ResourceNotFoundException("Cuenta con id" + idAccount + "no encontrada");
			
		}
	}
	
	@Override
	public AccountDTO getDetailsAcc(long idAccount) {
		
		AccountEntity account = getAccountById(idAccount);
				
		// probar el autowired para esto YESSSSSSSSSSSSSSSSSS

		UserEntity user = usRepo.getUserById(account.getIdUsuario().getUsuarioId());
		
		AccountDTO userDetails = new AccountDTO();
		
		userDetails.setCuentaID( account.getCuentaID() );
		userDetails.setFullNameuser( user.getFullName() );
		userDetails.setUserName(account.getUserName() );
		userDetails.setPassword( account.getPassword() );
		userDetails.setType_Account( account.getType_Account() );
		
		
		return userDetails;
	}
	
	@Override
	public void deleteAccount(long idAccount) {
		
		Optional<AccountEntity> accB = this.accountRepository.findById(idAccount);
		
		 if(accB.isPresent()) {
			 this.accountRepository.delete(accB.get());
				
				
		 }else {
			 throw new ResourceNotFoundException("Cuenta con id" + idAccount + "no encontrada");
				
		 }
		
	}

	

}
