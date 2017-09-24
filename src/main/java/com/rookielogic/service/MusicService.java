package com.rookielogic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rookielogic.model.Track;
import com.rookielogic.repository.MusicRepository;

@Component
public class MusicService {

	@Autowired
	private MusicRepository musicRepository;
	
	
	public MusicService(MusicRepository musicRepository) {
		super();
		this.musicRepository = musicRepository;
	}


	public List<Track> listAllTracks(){
		return musicRepository.listAllTracks();
	}
}
