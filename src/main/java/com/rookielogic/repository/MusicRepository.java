package com.rookielogic.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Repository;

import com.rookielogic.model.Track;

@Repository
public class MusicRepository {

	private List<Track> masterListOfTracks;
	
	 public MusicRepository() {
		 this.masterListOfTracks=readAllTracksFromDisk();
	}

	 public List<Track> readAllTracksFromDisk() {
		 List<Track> list=null;
		 try {
			Path rootPath=Paths.get("E:\\2014_latest");
			 if(Files.exists(rootPath) && Files.isDirectory(rootPath)){
				 list=Files.walk(rootPath)
						 
						 .filter(path -> FilenameUtils
								 			.getExtension(path.getFileName().toString()).equals("mp3"))
						 .limit(1)
				 		.map(track -> new Track(track))
				 		
				 		.peek( tracks -> System.out.println(tracks))
				 		.collect(Collectors.toList());
				 		
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return list;
	 }
	public List<Track> listAllTracks(){
		System.out.println("Total Tracks="+masterListOfTracks.size());
		return masterListOfTracks;
	}
	
	
}
