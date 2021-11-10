package eu.commean.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.commean.backend.data.TrafficCameraNode;
import eu.commean.backend.data.TrafficMeasurement;
import eu.commean.backend.repo.TrafficMeasurementRepository;

@Service
public class TrafficMeasurementServiceImpl implements TrafficMeasurementService {

	TrafficMeasurementRepository trafficMeasurementRepository;

	@Autowired
	public TrafficMeasurementServiceImpl(TrafficMeasurementRepository trafficMeasurementRepository) {
		this.trafficMeasurementRepository = trafficMeasurementRepository;
	}

	@Override
	public TrafficMeasurement addTrafficMeasurement(TrafficMeasurement tm) {
		return trafficMeasurementRepository.save(tm);
	}

	@Override
	public List<TrafficMeasurement> getAllTrafficMeasurements() {
		return (List<TrafficMeasurement>) trafficMeasurementRepository.findAll();
	}

	@Override
	public TrafficMeasurement getTrafficMeasurementById(int id) {

		return trafficMeasurementRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public List<TrafficMeasurement> getMeasurementsByTrafficCameraNode(TrafficCameraNode tcn) {
		return (List<TrafficMeasurement>) trafficMeasurementRepository.findAllByTrafficCameraNode(tcn);
	}



	@Override
	public void deleteTrafficMeasurementById(int id) {
		trafficMeasurementRepository.deleteById(id);

	}

	@Override
	public void createHypertable() {
		trafficMeasurementRepository.createHypertable("traffic_measurement", "timestamp");

	}

	@Override
	@Transactional
	//TODO implement querry to select by timespan
	public List<TrafficMeasurement> getAllMeasrumentsFromTimespan(TrafficCameraNode tcn, String interval) {
		return (List<TrafficMeasurement>) trafficMeasurementRepository.findAllByTimespan(tcn.getId(), interval);
	}

}
