package eu.commean.backend.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.geo.Point;

import lombok.Data;

@Data
public class NodeStatisticsDto {
	
	private int id;
	private List<TrafficMeasurementStatisticsRealtimeDto> measurements;
	

}
