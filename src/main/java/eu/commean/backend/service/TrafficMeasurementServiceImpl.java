package eu.commean.backend.service;

import eu.commean.backend.entity.Node;
import eu.commean.backend.entity.TrafficMeasurement;
import eu.commean.backend.repo.TrafficMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

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
	public TrafficMeasurement getTrafficMeasurementById(UUID id) {

		return trafficMeasurementRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public List<TrafficMeasurement> getMeasurementsByNode(Node tcn) {
		return (List<TrafficMeasurement>) trafficMeasurementRepository.findAllByNode(tcn);
	}



	@Override
	public void deleteTrafficMeasurementById(UUID id) {
		trafficMeasurementRepository.deleteById(id);

	}

	@Override
	public void createHypertable() {
		trafficMeasurementRepository.createHypertable("traffic_measurement", "timestamp");

	}

	@Override
	@Transactional
	public List<TrafficMeasurement> getAllMeasrumentsFromTimespan(UUID id, int days, int hours, int minutes, int seconds) {
		return (List<TrafficMeasurement>) trafficMeasurementRepository.findAllByTimespan(id, days, hours, minutes, seconds);
	}

	@Override
	public TrafficMeasurement getLatestMeasurementFromId(UUID id) {
		return trafficMeasurementRepository.findLatestById(id).orElse(null);
	}

	@Override
	/**
	 * Returns the current wait time on a given {@link Node}
	 */
	public int currentWaitTimeOnNode(UUID id) {
		return 0;
	}


}
