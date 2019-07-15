package io.java.spring.microservices.chartpositiondataservice.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.java.spring.microservices.chartpositiondataservice.models.ChartPosition;

@RestController
@RequestMapping("/positions")
public class ChartPositionResource {
	
	private List<ChartPosition> chartPositions;
	
	public ChartPositionResource() {
		chartPositions = new ArrayList<>();
		chartPositions.add(new ChartPosition("beautiful-people", 1, 1, 1));
		chartPositions.add(new ChartPosition("bad-guy", 2, 2, 2));
		chartPositions.add(new ChartPosition("senorita", 3, 3, 3));
		chartPositions.add(new ChartPosition("old-town-road", 4, 4, 4));
		chartPositions.add(new ChartPosition("talk", 5, 5, 5));
	}

	@RequestMapping("/{musicTrackId}")
	public ChartPosition getChartPosition(@PathVariable String musicTrackId) {
		return chartPositions.stream().filter(position -> position.getMusicTrackId().equals(musicTrackId)).findFirst().get();
	}
	
	@RequestMapping("")
	public List<ChartPosition> getChartPositions() {
		return chartPositions;
	}
}
