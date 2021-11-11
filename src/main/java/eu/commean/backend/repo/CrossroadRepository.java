package eu.commean.backend.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import eu.commean.backend.data.Crossroad;

@Repository
public interface CrossroadRepository extends CrudRepository<Crossroad, Integer> {

	List<Crossroad> findByCrossroadName(String crossroadName);

	@Override
	@Transactional
	@EntityGraph(attributePaths = { "trafficCameraNode" })
	List<Crossroad> findAll();

}
