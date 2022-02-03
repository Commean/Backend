package eu.commean.backend.service;

import eu.commean.backend.entity.TrafficCameraNode;
import eu.commean.backend.repo.TrafficCameraNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
	@Transactional
	public List<TrafficCameraNode> getAllTrafficCameraNodes() {
		return (List<TrafficCameraNode>) cameraNodeRepository.findAll();
	}

	@Override
	@Transactional
	public TrafficCameraNode getTrafficCameraNodeById(UUID id) {
		return cameraNodeRepository.findById(id).orElse(null);
	}

	@Override
	public Optional<TrafficCameraNode> getTrafficCameraNodesByName(String name) {
		return cameraNodeRepository.findByNameLike(name);
	}


	@Override
	public List<TrafficCameraNode> getAllTrafficCameraNodesWhereLocatioNotNull() {
		return (List<TrafficCameraNode>) cameraNodeRepository.findAllWhereLocationNotNull();
	}

	@Override
	public void deleteTrafficCameraNodeById(UUID id) {
		cameraNodeRepository.deleteById(id);

	}

}
