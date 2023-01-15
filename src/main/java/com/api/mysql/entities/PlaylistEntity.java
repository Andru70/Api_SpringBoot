package com.api.mysql.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="playlist")

public class PlaylistEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "playlistid")
	private int PlaylistID;
	
	@ManyToOne
	@JoinColumn(name = "cuentaid", referencedColumnName = "CuentaID")
	private AccountEntity account;
	
	@Column(name = "nombre")
	private String nombre;

	@OneToMany( mappedBy = "playlist" )
	private List<CancionEntity> canciones;

	public PlaylistEntity() {
		super();
	}

	public PlaylistEntity(int playlistID, AccountEntity account, String nombre, List<CancionEntity> canciones) {
		PlaylistID = playlistID;
		this.account = account;
		this.nombre = nombre;
		this.canciones = canciones;
	}

	public PlaylistEntity(int playlistID, String nombre) {

		PlaylistID = playlistID;
		this.nombre = nombre;
	}

	public PlaylistEntity(String nombre) {

		this.nombre = nombre;
	}



	public Integer getPlaylistID() {
		return PlaylistID;
	}


	public void setPlaylistID(Integer playlistID) {
		PlaylistID = playlistID;
	}


	public AccountEntity getAccount() {
		return account;
	}


	public void setAccount(AccountEntity account) {
		this.account = account;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
