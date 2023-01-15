package com.api.mysql.dto;


public class AccountDTO {
	
private long CuentaID;
	
	private String fullNameuser; 
	
	private String UserName;
	
	private String Password;
	
	private String Type_Account;
	
	

	public AccountDTO() {
		super();
	}

	public AccountDTO(long cuentaID, String fullNameuser, String userName, String password, String type_Account ) {
		
		CuentaID = cuentaID;
		this.fullNameuser = fullNameuser;
		UserName = userName;
		Password = password;
		Type_Account = type_Account;
	}

	public long getCuentaID() {
		return CuentaID;
	}

	public void setCuentaID(long cuentaID) {
		CuentaID = cuentaID;
	}

	public String getFullNameuser() {
		return fullNameuser;
	}

	public void setFullNameuser(String fullNameuser) {
		this.fullNameuser = fullNameuser;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getType_Account() {
		return Type_Account;
	}

	public void setType_Account(String type_Account) {
		Type_Account = type_Account;
	}

}
