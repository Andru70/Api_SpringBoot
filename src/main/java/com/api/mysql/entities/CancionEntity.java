package com.api.mysql.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="cancion")

public class CancionEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CancionId")
	private int CancionId;
	
	@ManyToOne
	@JoinColumn(name = "PlaylistId")
	private PlaylistEntity playlist;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "artista")
	private String artista;
	
	@Column(name = "genero")
	private String genero;
	
	@Column(name = "compositor")
	private String compositor;
	
	@Column(name = "album")
	private String album;
	
	@Column(name = "date")
	private Date date;

	public CancionEntity() {
		super();
	}

	public CancionEntity(int cancionId, PlaylistEntity playlist, String nombre, String artista,
						 String genero, String compositor, String album, Date date) {
		CancionId = cancionId;
		this.playlist = playlist;
		this.nombre = nombre;
		this.artista = artista;
		this.genero = genero;
		this.compositor = compositor;
		this.album = album;
		this.date = date;
	}

	public CancionEntity(PlaylistEntity playlist, String nombre, String artista,
						 String genero, String compositor, String album, Date date) {

		this.playlist = playlist;
		this.nombre = nombre;
		this.artista = artista;
		this.genero = genero;
		this.compositor = compositor;
		this.album = album;
		this.date = date;
	}

	public CancionEntity(int cancionId, String nombre, String artista, String genero, String compositor,
						 String album, Date date) {
		CancionId = cancionId;
		this.nombre = nombre;
		this.artista = artista;
		this.genero = genero;
		this.compositor = compositor;
		this.album = album;
		this.date = date;
	}

	public CancionEntity(String nombre, String artista, String genero, String compositor,
						 String album, Date date) {

		this.nombre = nombre;
		this.artista = artista;
		this.genero = genero;
		this.compositor = compositor;
		this.album = album;
		this.date = date;
	}

	public Integer getCancionId() {
		return CancionId;
	}

	public void setCancionId(Integer cancionId) {
		CancionId = cancionId;
	}

	public PlaylistEntity getPlaylist() {
		return playlist;
	}

	public void setPlaylist(PlaylistEntity playlist) {
		this.playlist = playlist;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCompositor() {
		return compositor;
	}

	public void setCompositor(String compositor) {
		this.compositor = compositor;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	

}