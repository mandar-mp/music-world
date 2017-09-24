package com.rookielogic.model;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Artist {

	@JsonProperty
	private long id;
	@JsonProperty
	private String artist;
	@JsonProperty
	private LocalDate dateOfBirth;
	
	public Artist(long id, String artist, LocalDate dateOfBirth) {
		super();
		this.id = id;
		this.artist = artist;
		this.dateOfBirth = dateOfBirth;
	}
	
	
}
