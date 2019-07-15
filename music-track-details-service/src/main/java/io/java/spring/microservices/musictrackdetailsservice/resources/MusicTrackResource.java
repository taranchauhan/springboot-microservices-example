package io.java.spring.microservices.musictrackdetailsservice.resources;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.java.spring.microservices.musictrackdetailsservice.models.MusicTrack;

@RestController
@RequestMapping("/tracks")
public class MusicTrackResource {

	@RequestMapping("/{musicTrackId}")
	public MusicTrack getMusicTrackDetails(@PathVariable String musicTrackId) {
		Map<String, MusicTrack> tracks = new HashMap<>();
		tracks.put("beautiful-people", new MusicTrack(musicTrackId, "Ed Sheeran Featuring Khalid", "Beautiful people"));
		tracks.put("bad-guy", new MusicTrack(musicTrackId, "Billie Eilish", "Bad guy"));
		tracks.put("senorita", new MusicTrack(musicTrackId, "Shawn Mendes & Camila Cabello", "Senorita"));
		tracks.put("old-town-road", new MusicTrack(musicTrackId, "Lil Nas X Featuring Billy Ray Cyrus", "Old Town Road"));
		tracks.put("talk", new MusicTrack(musicTrackId, "Khalid", "Talk"));
		return tracks.get(musicTrackId);
	}
}
