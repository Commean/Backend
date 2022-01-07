package eu.commean.backend.repo;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import eu.commean.backend.data.Crossroad;
import eu.commean.backend.data.TrafficCameraNode;

public interface TrafficCameraNodeRepository extends CrudRepository<TrafficCameraNode, Integer> {
	Iterable<TrafficCameraNode> findByCrossroad(Crossroad crossroad);

	Optional<TrafficCameraNode> findById(UUID id);

	void deleteById(UUID id);
}