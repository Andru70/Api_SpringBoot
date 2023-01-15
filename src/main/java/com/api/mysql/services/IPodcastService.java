package com.api.mysql.services;

import java.util.List;

import com.api.mysql.entities.PodcastEntity;

public interface IPodcastService {
	
	PodcastEntity createPodcast(PodcastEntity podcast);
	
	PodcastEntity updatePodcast(PodcastEntity podcast);
	
	List<PodcastEntity> getAllPodcasts();
	
	PodcastEntity getPodcastById(int idpodcast);
	
//	AccountDTO getDetailsAcc(long idAccount);
	
	void deletePodcast(int idpodcast);

}
