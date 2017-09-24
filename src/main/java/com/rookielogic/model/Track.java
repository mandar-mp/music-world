package com.rookielogic.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttributeView;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Track {

	@JsonProperty
	private long id;
	@JsonProperty
	private String title;
	@JsonProperty
	private String album;
	@JsonProperty
	private List<Genre> genre;
	@JsonProperty
	private int releaseYear;
	@JsonProperty
	private List<Artist> artist;
	
	public Track(long id, String title, String album, List<Genre> genre, int releaseYear, List<Artist> artist) {
		super();
		this.id = id;
		this.title = title;
		this.album = album;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.artist = artist;
	}
	
	public Track(Path path){
		
		this.title=path.getFileName().toString();
		
//			this.album=Files.getAttribute(path, "Album").toString();
//			this.genre=Arrays.asList(new Genre(1,Files.getAttribute(path, "Genre").toString()));
//			this.releaseYear=Integer.parseInt(Files.getAttribute(path, "Year").toString());
//			this.artist=Arrays.asList(new Artist(1,Files.getAttribute(path, "Contributing artists").toString(),LocalDate.now()));
		
	}
	
	@Override
	public String toString() {
		return this.title;
	}
}
