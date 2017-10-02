package com.rookielogic.entity;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.rookielogic.model.Track;

@Document(collection = "trackDetails")
public class TrackDetailsEntity {
	
	@Id
	private ObjectId id;
	
	private Map<String,String> attributes;

	public TrackDetailsEntity(Track track) {
		this.attributes=track.getAttributes();
	}

	public TrackDetailsEntity() {
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	
	
}
