package com.api.mysql.services;

import java.util.List;

import com.api.mysql.entities.CancionEntity;

public interface ICancionService {
	
	CancionEntity createCancion(CancionEntity cancion);
	
	CancionEntity updateCancion(CancionEntity cancion);
	
	List<CancionEntity> getAllCancions();
	
	CancionEntity getCancionById(int idcancion);
	
	void deleteCancion(int idcancion);

}
