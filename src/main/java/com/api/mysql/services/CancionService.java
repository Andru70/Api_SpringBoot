package com.api.mysql.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.api.mysql.entities.CancionEntity;
import com.api.mysql.repositories.ICancionRepository;

@Service
public class CancionService implements ICancionService{
	
	@Autowired
	private ICancionRepository cancionRepository;

	@Override
	public CancionEntity createCancion(CancionEntity cancion) {
		return cancionRepository.save(cancion);
	}

	@Override
	public CancionEntity updateCancion(CancionEntity cancion) {
		
		Optional<CancionEntity> cancionB = this.cancionRepository.findById(cancion.getCancionId());
		
		if(cancionB.isPresent()) {
			
			CancionEntity cancionUpdate = cancionB.get();
			
			cancionUpdate.setNombre(cancion.getNombre());
			cancionUpdate.setGenero(cancion.getGenero());
			cancionUpdate.setCompositor(cancion.getCompositor());
			cancionUpdate.setArtista(cancion.getArtista());
			cancionUpdate.setAlbum(cancion.getAlbum());
			cancionUpdate.setDate(cancion.getDate());
			
			cancionRepository.save(cancionUpdate);
			
			return cancionUpdate;
			
				
		}else {
			throw new ResourceNotFoundException("Cancion con id" + cancion.getCancionId() + "no encontrada");	
		}
	}

	@Override
	public List<CancionEntity> getAllCancions() {
		return cancionRepository.findAll();
	}

	@Override
	public CancionEntity getCancionById(int idcancion) {

		Optional<CancionEntity> cancionB = this.cancionRepository.findById(idcancion);
		
		if(cancionB.isPresent()) {
			return cancionB.get();
			
			
		}else {
			throw new ResourceNotFoundException("Cancion con id" + idcancion + "no encontrada");
			
		}
		
	}

	@Override
	public void deleteCancion(int idcancion) {
		
		Optional<CancionEntity> cancionB = this.cancionRepository.findById(idcancion);
		
		 if(cancionB.isPresent()) {
			 this.cancionRepository.delete(cancionB.get());
				
				
		 }else {
			 throw new ResourceNotFoundException("Cancion con id" + idcancion + "no encontrada");
				
		 }
		
	}

}
