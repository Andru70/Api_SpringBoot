package com.api.mysql.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "podcast")

public class PodcastEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "podcastid")
	private int PodcastID;
	
	@ManyToOne
	@JoinColumn(name = "cuentaid", referencedColumnName = "CuentaID")
	private AccountEntity account;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "autor")
	private String autor;
	
	@Column(name = "episodios")
	private Integer episodios;

	public PodcastEntity() {
		super();
	}

	public PodcastEntity(int podcastID, AccountEntity account, String nombre, String autor, Integer episodios) {
		PodcastID = podcastID;
		this.account = account;
		this.nombre = nombre;
		this.autor = autor;
		this.episodios = episodios;
	}

	public PodcastEntity(AccountEntity account, String nombre, String autor, Integer episodios) {

		this.nombre = nombre;
		this.autor = autor;
		this.episodios = episodios;
		this.account = account;
	}

	public PodcastEntity(int podcastID, String nombre, String autor, Integer episodios) {

		PodcastID = podcastID;
		this.nombre = nombre;
		this.autor = autor;
		this.episodios = episodios;
	}

	public PodcastEntity(String nombre, String autor, Integer episodios) {

		this.nombre = nombre;
		this.autor = autor;
		this.episodios = episodios;
	}


	public Integer getPodcastID() {
		return PodcastID;
	}

	public void setPodcastID(int podcastID) {
		PodcastID = podcastID;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getEpisodios() {
		return episodios;
	}

	public void setEpisodios(Integer episodios) {
		this.episodios = episodios;
	}
	
	
}
