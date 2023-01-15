package com.api.mysql.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario")

public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usuarioid")
	private long usuarioid;
	
	@Column(name = "fullname")
	private String fullname;

	@OneToOne(mappedBy="idusuario")
	
	private AccountEntity account;
	
	
	public UserEntity() {
		super();
	}
	

	public UserEntity(long usuarioid, String fullname, AccountEntity account) {
		super();
		this.usuarioid = usuarioid;
		this.fullname = fullname;
		this.account = account;
	}

	public UserEntity(long usuarioid, String fullname) {
		super();
		this.usuarioid = usuarioid;
		this.fullname = fullname;

	}

	public UserEntity(String fullname) {
		super();
		this.fullname = fullname;

	}

	public long getUsuarioId() {
		return usuarioid;
	}

	public void setUsuarioId(long usuarioid) {
		this.usuarioid = usuarioid;
	}

	public String getFullName() {
		return fullname;
	}

	public void setFullName(String fullname) {
		this.fullname = fullname;
	}


	
}
