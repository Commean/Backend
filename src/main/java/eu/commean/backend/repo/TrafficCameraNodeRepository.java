package eu.commean.backend.repo;

import eu.commean.backend.data.Crossroad;
import eu.commean.backend.data.TrafficCameraNode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TrafficCameraNodeRepository extends CrudRepository<TrafficCameraNode, UUID> {
	Iterable<TrafficCameraNode> findByCrossroad(Crossroad crossroad);

	Iterable<TrafficCameraNode> findAllWhereLocationNotNull();
}