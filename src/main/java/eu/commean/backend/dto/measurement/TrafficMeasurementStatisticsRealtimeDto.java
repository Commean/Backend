package eu.commean.backend.dto.measurement;

import eu.commean.backend.entity.TrafficMeasurement;
import eu.commean.backend.enums.TrafficSituation;
import lombok.Data;

import java.util.UUID;

@Data
public class TrafficMeasurementStatisticsRealtimeDto {
	private UUID tcnId;
	private int currentCars;
	private int currentTrucks;
	private int averageTimeInPicture;
	private TrafficSituation trafficSituation;

	public TrafficSituation calcTrafficSituation() {
		int trafficAverage = (this.currentCars + this.currentTrucks) * this.averageTimeInPicture;

		if (trafficAverage <= 0)
			return TrafficSituation.FEW;
		if (trafficAverage > 0 && trafficAverage <= 10)
			return TrafficSituation.SOME;
		if (trafficAverage > 10 && trafficAverage <= 30)
			return TrafficSituation.NORMAL;
		if (trafficAverage > 30 && trafficAverage <= 60)
			return TrafficSituation.HIGH;
		if (trafficAverage > 60)
			return TrafficSituation.JAM;
		return TrafficSituation.NONE;

	}

	public TrafficMeasurementStatisticsRealtimeDto(UUID trafficCameraNodeId,int currentCars, int currentTrucks, int averageTimeInPicture) {
		super();
		this.tcnId =trafficCameraNodeId;
		this.currentCars = currentCars;
		this.currentTrucks = currentTrucks;
		this.averageTimeInPicture = averageTimeInPicture;
		this.trafficSituation = calcTrafficSituation();
	}

	static public TrafficMeasurementStatisticsRealtimeDto convertToDto(TrafficMeasurement tm) {
		return new TrafficMeasurementStatisticsRealtimeDto(tm.getTrafficCameraNode().getId(),tm.getCars(), tm.getTrucks	(),
				tm.getAverageTimeInPicture());

	}

}
