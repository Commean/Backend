package eu.commean.backend.service;

import java.util.List;

import eu.commean.backend.data.TrafficCameraNode;
import eu.commean.backend.data.TrafficMeasurement;

public interface TrafficMeasurementService {

	TrafficMeasurement addTrafficMeasurement(TrafficMeasurement tm);

	List<TrafficMeasurement> getAllTrafficMeasurements();

	TrafficMeasurement getTrafficMeasurementById(int id);

	List<TrafficMeasurement> getMeasurementsByTrafficCameraNode(TrafficCameraNode tcn);

	List<TrafficMeasurement> getAllMeasrumentsFromTimespan(int id,int days);
	
	TrafficMeasurement getLatestMeasurementFromId(int id);

	void deleteTrafficMeasurementById(int id);

	void createHypertable();

}
