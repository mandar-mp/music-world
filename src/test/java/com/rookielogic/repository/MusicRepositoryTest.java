package com.rookielogic.repository;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class MusicRepositoryTest {

	@Test
	public void listAllTracksTest(){
		assertNotNull("Test to read all tracks from disk and print.", new MusicRepository().listAllTracks());
	}
}
