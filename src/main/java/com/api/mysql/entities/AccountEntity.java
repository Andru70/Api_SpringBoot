package com.api.mysql.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cuenta")

public class AccountEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cuentaid")
	private long CuentaID;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String Password;
	
	@Column(name = "type_account")
	private String Type_Account;
	
	@OneToOne
	@JoinColumn(name = "idusuario", referencedColumnName = "usuarioid") //No colocar solamente el parámetro name
	private UserEntity idusuario;
	
	@OneToMany( mappedBy = "account" )
	private List<PlaylistEntity> playlists;
	
	@OneToMany( mappedBy = "account" )
	private List<PodcastEntity> podcasts;
	

	public AccountEntity() {
		super();
	}
	
	
	public AccountEntity(long cuentaID, String username, String password, String type_Account, UserEntity idusuario,
			List<PlaylistEntity> playlists, List<PodcastEntity> podcasts) {
		
		CuentaID = cuentaID;
		this.username = username;
		Password = password;
		Type_Account = type_Account;
		this.idusuario = idusuario;
		this.playlists = playlists;
		this.podcasts = podcasts;
	}

	public AccountEntity(long cuentaID, String username, String password, String type_Account, UserEntity idusuario) {

		CuentaID = cuentaID;
		this.username = username;
		Password = password;
		Type_Account = type_Account;
		this.idusuario = idusuario;

	}

	public AccountEntity(long cuentaID, String username, String password, String type_Account) {

		CuentaID = cuentaID;
		this.username = username;
		Password = password;
		Type_Account = type_Account;
	}

	public AccountEntity(String username, String password, String type_Account) {

		this.username = username;
		Password = password;
		Type_Account = type_Account;
		this.idusuario = idusuario;
		this.playlists = playlists;
		this.podcasts = podcasts;
	}



	public long getCuentaID() {
		return CuentaID;
	}

	public void setCuentaID(long cuentaID) {
		CuentaID = cuentaID;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
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

	public UserEntity getIdUsuario() {
		return idusuario;
	}

	public void setIdUsuario(UserEntity idusuario) {
		this.idusuario = idusuario;
	}

	
}