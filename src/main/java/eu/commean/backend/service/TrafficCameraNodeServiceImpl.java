package eu.commean.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.commean.backend.data.Crossroad;
import eu.commean.backend.data.TrafficCameraNode;
import eu.commean.backend.repo.TrafficCameraNodeRepository;

@Service
public class TrafficCameraNodeServiceImpl implements TrafficCameraNodeService {

	TrafficCameraNodeRepository cameraNodeRepository;

	@Autowired
	public TrafficCameraNodeServiceImpl(TrafficCameraNodeRepository cameraNodeRepository) {
		this.cameraNodeRepository = cameraNodeRepository;
	}

	@Override
	public TrafficCameraNode addTrafficCameraNode(TrafficCameraNode tcn) {
		return cameraNodeRepository.save(tcn);
	}

	@Override
	public List<TrafficCameraNode> getAllTrafficCameraNodes() {
		return (List<TrafficCameraNode>) cameraNodeRepository.findAll();
	}

	@Override
	public TrafficCameraNode getTrafficCameraNodeById(int id) {
		return cameraNodeRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public List<TrafficCameraNode> getTrafficCameraNodesByCrossroad(Crossroad c) {		
		return cameraNodeRepository.findByCrossroad(c);
	}

	@Override
	public void deleteTrafficCameraNodeById(int id) {
		cameraNodeRepository.deleteById(id);

	}

}
