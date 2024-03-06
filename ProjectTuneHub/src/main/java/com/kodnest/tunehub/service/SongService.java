package com.kodnest.tunehub.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Song;

@Service
public interface SongService {

	 

	public boolean songExists(String name);
	
	public void addSong(Song song);

	public List<Song> fetchAllSongs();

	public void updateSong(Song s);

}
