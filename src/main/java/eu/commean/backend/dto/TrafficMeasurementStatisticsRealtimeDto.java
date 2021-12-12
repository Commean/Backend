package eu.commean.backend.dto;

import eu.commean.backend.data.TrafficMeasurement;
import eu.commean.backend.enums.TrafficSituation;
import lombok.Data;

@Data
public class TrafficMeasurementStatisticsRealtimeDto {
	private int tcnId;
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

	public TrafficMeasurementStatisticsRealtimeDto(int trafficCameraNodeId,int currentCars, int currentTrucks, int averageTimeInPicture) {
		super();
		this.tcnId =trafficCameraNodeId;
		this.currentCars = currentCars;
		this.currentTrucks = currentTrucks;
		this.trafficSituation = calcTrafficSituation();
	}

	static public TrafficMeasurementStatisticsRealtimeDto convertToDto(TrafficMeasurement tm) {
		return new TrafficMeasurementStatisticsRealtimeDto(tm.getTrafficCameraNode().getId(),tm.getCarIn(), tm.getTruckIn(),
				tm.getAverageTimeInPicture());

	}

}
