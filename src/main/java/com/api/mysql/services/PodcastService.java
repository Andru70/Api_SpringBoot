package com.api.mysql.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.api.mysql.entities.PodcastEntity;
import com.api.mysql.repositories.IPodcastRepository;

@Service
public class PodcastService implements IPodcastService{
	
	@Autowired
	private IPodcastRepository podcastRepository;

	@Override
	public PodcastEntity createPodcast(PodcastEntity podcast) {
		return podcastRepository.save(podcast);
	}

	@Override
	public PodcastEntity updatePodcast(PodcastEntity podcast) {
		
		Optional<PodcastEntity> podcastB = this.podcastRepository.findById(podcast.getPodcastID());
		
		if(podcastB.isPresent()) {
			
			PodcastEntity podcastUpdate = podcastB.get();
			
			podcastUpdate.setNombre(podcast.getNombre());
			podcastUpdate.setAutor(podcast.getAutor());
			podcastUpdate.setEpisodios(podcast.getEpisodios());
			
			podcastRepository.save(podcastUpdate);
			
			return podcastUpdate;
			
				
		}else {
			throw new ResourceNotFoundException("Podcast con id" + podcast.getPodcastID() + "no encontrado");	
		}
		
		
	}

	@Override
	public List<PodcastEntity> getAllPodcasts() {
		return podcastRepository.findAll();
	}

	@Override
	public PodcastEntity getPodcastById(int idpodcast) {

		Optional<PodcastEntity> podcastB = this.podcastRepository.findById(idpodcast);
		
		if(podcastB.isPresent()) {
			return podcastB.get();
			
			
		}else {
			throw new ResourceNotFoundException("Podcast con id" + idpodcast + "no encontrado");
			
		}
		
	}

	@Override
	public void deletePodcast(int idpodcast) {
		
		Optional<PodcastEntity> podcastB = this.podcastRepository.findById(idpodcast);
		
		 if(podcastB.isPresent()) {
			 this.podcastRepository.delete(podcastB.get());
				
				
		 }else {
			 throw new ResourceNotFoundException("Podcast con id" + idpodcast + "no encontrado");
				
		 }
		
	}

}
