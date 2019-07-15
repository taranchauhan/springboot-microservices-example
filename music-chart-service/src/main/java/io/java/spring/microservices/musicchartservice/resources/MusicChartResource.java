package io.java.spring.microservices.musicchartservice.resources;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.java.spring.microservices.musicchartservice.helpers.ChartItemComparator;
import io.java.spring.microservices.musicchartservice.models.ChartItem;
import io.java.spring.microservices.musicchartservice.models.ChartPosition;
import io.java.spring.microservices.musicchartservice.models.MusicTrack;

@RestController
@RequestMapping("/chart")
public class MusicChartResource {

	List<ChartPosition> chartPositions;
	List<ChartItem> chart;

	public MusicChartResource() {
		RestTemplate getChartPositionsTemplate = new RestTemplate();
		ResponseEntity<List<ChartPosition>> response = getChartPositionsTemplate.exchange(
		  "http://localhost:8082/positions",
		  HttpMethod.GET,
		  null,
		  new ParameterizedTypeReference<List<ChartPosition>>(){});
		chartPositions = response.getBody();
		
		chart = chartPositions.stream()
				.map(position -> { 
					RestTemplate getMusicTrackTemplate = new RestTemplate();
					MusicTrack musicTrack = getMusicTrackTemplate.getForObject("http://localhost:8081/tracks/" + position.getMusicTrackId(), MusicTrack.class);
					return new ChartItem(
						musicTrack,
						position
					);
				})
				.collect(Collectors.toList());
	}

	@RequestMapping("")
	public List<ChartItem> getChart() {
		Collections.sort(chart, new ChartItemComparator());
		return chart;
	}

	@RequestMapping("/{artistName}")
	public List<ChartItem> getChartItemsByArtist(@PathVariable String artistName) {
		List<ChartItem> tracksByArtist = chart.stream().filter(
				chartItem -> chartItem.getTrack().getArtistName().toLowerCase().contains(artistName.toLowerCase()))
				.collect(Collectors.toList());
		Collections.sort(tracksByArtist, new ChartItemComparator());

		return tracksByArtist;
	}
}
