package com.rookielogic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Genre {

	@JsonProperty
	private long id;
	@JsonProperty
	private String genre;
	
	public Genre(long id, String genre) {
		super();
		this.id = id;
		this.genre = genre;
	}
	
	
}
