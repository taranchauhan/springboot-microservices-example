package io.java.spring.microservices.musicchartservice.resources;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.java.spring.microservices.musicchartservice.helpers.ChartItemComparator;
import io.java.spring.microservices.musicchartservice.helpers.ChartItemCustomSerializer;
import io.java.spring.microservices.musicchartservice.models.ChartItem;
import io.java.spring.microservices.musicchartservice.models.ChartPosition;
import io.java.spring.microservices.musicchartservice.models.MusicTrack;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/chart")
public class MusicChartResource {

	List<ChartPosition> chartPositions;
	List<ChartItem> chart;

	@Autowired
	WebClient.Builder webClientBuilder;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getChart() {
		fetchData();
		Collections.sort(chart, new ChartItemComparator());
		return parseResponse(chart);
	}

	@RequestMapping(value = "/{artistName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getChartItemsByArtist(@PathVariable String artistName) {
		fetchData();
		List<ChartItem> tracksByArtist = chart.stream().filter(
				chartItem -> chartItem.getTrack().getArtistName().toLowerCase().contains(artistName.toLowerCase()))
				.collect(Collectors.toList());
		Collections.sort(tracksByArtist, new ChartItemComparator());

		return parseResponse(tracksByArtist);
	}

	public void fetchData() {
		if (chartPositions == null && chart == null) {
			Flux<ChartPosition> chartPositionsFlux = webClientBuilder.build().get()
					.uri("http://chart-position-data-service/positions").accept(MediaType.APPLICATION_JSON).retrieve()
					.bodyToFlux(ChartPosition.class);

			chartPositions = chartPositionsFlux.collectList().block();

			chart = chartPositions.stream().map(position -> {
				MusicTrack musicTrack = webClientBuilder.build().get()
						.uri("http://music-track-details-service/tracks/" + position.getMusicTrackId()).retrieve()
						.bodyToMono(MusicTrack.class).block();

				return new ChartItem(musicTrack, position);
			}).collect(Collectors.toList());
		}
	}

	public ResponseEntity<String> parseResponse(List<ChartItem> chart) {
		ObjectMapper mapper = new ObjectMapper();

		SimpleModule module = new SimpleModule();
		module.addSerializer(ChartItem.class, new ChartItemCustomSerializer());
		mapper.registerModule(module);

		String chartJSON = "";
		String errorJSON = "";
		JsonNode errorNode = null;
		try {
			chartJSON = mapper.writeValueAsString(chart);

			errorNode = mapper.createObjectNode();
			errorJSON = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(errorNode);
		} catch (JsonProcessingException e) {
			((ObjectNode) errorNode).put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorJSON);
		}

		return new ResponseEntity<String>(chartJSON, HttpStatus.OK);
	}
}
