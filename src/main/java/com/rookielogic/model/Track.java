package com.rookielogic.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttributeView;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.LyricsHandler;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@JsonIgnore
	private Map<String,String> attributes=new HashMap<>();

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

		try {
			parseAudioFileAttributes(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.title=attributes.get("title");
		this.album=attributes.get("album");
		//			this.genre=Arrays.asList(new Genre(1,Files.getAttribute(path, "Genre").toString()));
		//			this.releaseYear=Integer.parseInt(Files.getAttribute(path, "Year").toString());
		//			this.artist=Arrays.asList(new Artist(1,Files.getAttribute(path, "Contributing artists").toString(),LocalDate.now()));

	}

	public void parseAudioFileAttributes(Path path) throws Exception{

		BodyContentHandler handler = new BodyContentHandler();
		Metadata metadata = new Metadata();
		InputStream inputstream = Files.newInputStream(path);
		ParseContext parseContext = new ParseContext();
		Mp3Parser  Mp3Parser = new  Mp3Parser();

		Mp3Parser.parse(inputstream, handler, metadata, parseContext);

		//		System.out.println("Contents of the document:" + handler.toString());

		for(String attributeName : metadata.names()) {		        
			System.out.println(attributeName + "= " + metadata.get(attributeName));
			this.attributes.put(attributeName.substring(attributeName.indexOf(":")+1),metadata.get(attributeName));
		}
	}

	@Override
	public String toString() {
		return "Track Details: "+
				" \""+this.title+"\" "+
				" \""+this.album+"\" ";
	}
}
