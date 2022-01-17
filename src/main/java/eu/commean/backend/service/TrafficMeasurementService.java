package eu.commean.backend.service;

import java.util.List;
import java.util.UUID;

import eu.commean.backend.data.TrafficCameraNode;
import eu.commean.backend.data.TrafficMeasurement;

public interface TrafficMeasurementService {

	TrafficMeasurement addTrafficMeasurement(TrafficMeasurement tm);

	List<TrafficMeasurement> getAllTrafficMeasurements();

	TrafficMeasurement getTrafficMeasurementById(UUID id);

	List<TrafficMeasurement> getMeasurementsByTrafficCameraNode(TrafficCameraNode tcn);

	List<TrafficMeasurement> getAllMeasrumentsFromTimespan(UUID id,int days);
	
	TrafficMeasurement getLatestMeasurementFromId(UUID id);

	void deleteTrafficMeasurementById(UUID id);

	void createHypertable();
}
