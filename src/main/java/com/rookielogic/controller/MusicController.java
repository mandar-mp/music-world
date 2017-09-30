package com.rookielogic.controller;

import java.util.List;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookielogic.model.Track;
import com.rookielogic.service.MusicService;

@RestController
public class MusicController {

	private MusicService musicService;
	
	public MusicController(MusicService musicService) {
		super();
		this.musicService = musicService;
	}



	@RequestMapping(path="/allTracks")
	public List<Track> listAllTracks(){
		return musicService.listAllTracks();
	}
	
	
}
