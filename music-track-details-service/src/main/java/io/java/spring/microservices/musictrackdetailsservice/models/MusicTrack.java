package io.java.spring.microservices.musictrackdetailsservice.models;

public class MusicTrack {
	private String id;
	private String artistName;
	private String songName;
	
	public MusicTrack(String id, String artistName, String songName) {
		super();
		this.id = id;
		this.artistName = artistName;
		this.songName = songName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getSongName() {
		return songName;
	}
	public void setSongName(String songName) {
		this.songName = songName;
	}
}
