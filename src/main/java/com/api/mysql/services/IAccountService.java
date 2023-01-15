package com.api.mysql.services;

import java.util.List;

import com.api.mysql.dto.AccountDTO;
import com.api.mysql.entities.AccountEntity;

public interface IAccountService {
	
	AccountEntity createAccount(AccountEntity account);
	
	AccountEntity updateAccount(AccountEntity account);
	
	List<AccountEntity> getAllAccounts();
	
	AccountEntity getAccountById(long idAccount);
	
	AccountDTO getDetailsAcc(long idAccount);
	
	void deleteAccount(long idAccount);

}
