package com.rookielogic.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import com.rookielogic.entity.TrackDetailsEntity;
import com.rookielogic.model.Track;

@Repository
public class MusicRepository {

	private List<Track> masterListOfTracks;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	 MongoOperations mongoOperations;
	
	public MusicRepository() {
		
	}
	
	public List<Track> listAllTracks(){
		logger.info("Total Tracks="+masterListOfTracks.size());
		return masterListOfTracks;
	}

	public boolean gatherTrackDetailsInDB(){
		this.masterListOfTracks=readAllTracksFromDisk();
		List<TrackDetailsEntity> trackDetailEntityList=masterListOfTracks.stream()
																		  .map(track -> new TrackDetailsEntity(track))
																		  .collect(Collectors.toList());
		
		try {
			for (TrackDetailsEntity trackDetailsEntity : trackDetailEntityList) {
				logger.info("Inserting track : "+trackDetailsEntity.getAttributes().get("title"));
				mongoOperations.save(trackDetailsEntity, "trackDetails");
				logger.info("Track details inserted");
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

	private List<Track> readAllTracksFromDisk() {
		List<Track> list=null;
		try {
			Path rootPath=Paths.get("E:\\2014_latest");
			if(Files.exists(rootPath) && Files.isDirectory(rootPath)){
				list=Files.walk(rootPath)

						.filter(path -> FilenameUtils
								.getExtension(path.getFileName().toString()).equals("mp3"))
//						.limit(10)
						.map(track -> new Track(track))
						//				 		.peek( tracks -> System.out.println(tracks))
						.collect(Collectors.toList());

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
