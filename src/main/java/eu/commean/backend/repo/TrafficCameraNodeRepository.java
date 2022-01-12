package eu.commean.backend.repo;

import eu.commean.backend.data.Crossroad;
import eu.commean.backend.data.TrafficCameraNode;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TrafficCameraNodeRepository extends CrudRepository<TrafficCameraNode, UUID> {
	Iterable<TrafficCameraNode> findByCrossroad(Crossroad crossroad);
}