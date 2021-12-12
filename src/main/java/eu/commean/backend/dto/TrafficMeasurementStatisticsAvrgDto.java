package eu.commean.backend.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class TrafficMeasurementStatisticsAvrgDto {

	private int averageCars;
	private int averageTrucks;
	private int averageWaitTime;

	private Instant TrafficPeakTime;
	private int TrafficPeakVehicles;

}
