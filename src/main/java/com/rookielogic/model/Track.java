package com.rookielogic.model;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Document(collection="trackDetails")
public class Track {

	@Id
	@JsonProperty
	private long id;
	@JsonProperty
	private String title;
	@JsonProperty
	private String album;
	@JsonProperty
	private List<String> genre;
	@JsonProperty
	private String releaseYear;
	@JsonProperty
	private List<String> artist;
	@JsonIgnore
	private Map<String,String> attributes=new HashMap<>();

	public Track(long id, String title, String album, List<String> genre, String releaseYear, List<String> artist) {
		super();
		this.id = id;
		this.title = title;
		this.album = album;
		this.genre = genre;
		this.releaseYear = releaseYear;
		this.artist = artist;
	}

	public Track(Path path){

		try {
			parseAudioFileAttributes(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.title=attributes.get("title");
		this.album=attributes.get("album");
		if(null!=attributes.get("artist"))
		this.artist=Arrays.asList(attributes.get("artist").split(","));
		this.releaseYear=attributes.get("Year");
		if(null!=attributes.get("genre"))
		this.genre=Arrays.asList(attributes.get("genre").split(","));
	}

	private void parseAudioFileAttributes(Path path) throws Exception{

		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		InputStream inputstream = Files.newInputStream(path);
		ParseContext parseContext = new ParseContext();
		Mp3Parser  Mp3Parser = new  Mp3Parser();

		Mp3Parser.parse(inputstream, handler, metadata, parseContext);

		for(String attributeName : metadata.names()) {		        
//			System.out.println(attributeName + "= " + metadata.get(attributeName));
			this.attributes.put(attributeName.substring(attributeName.indexOf(":")+1),metadata.get(attributeName));
		}
	}

	@Override
	public String toString() {
		return "Track Details: "+
				" \""+this.title+"\" "+
				" \""+this.album+"\" "+
				" \""+this.releaseYear+"\" "+
				" \""+this.artist+"\" "+
				" \""+this.genre+"\" ";
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	
}
