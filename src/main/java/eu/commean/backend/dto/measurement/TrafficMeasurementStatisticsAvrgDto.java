package eu.commean.backend.dto.measurement;

import lombok.Data;

import java.time.Instant;

@Data
public class TrafficMeasurementStatisticsAvrgDto {

	private int averageCars;
	private int averageTrucks;
	private int averageWaitTime;

	private Instant trafficPeakTime;
	private int trafficPeakVehicles;

}
