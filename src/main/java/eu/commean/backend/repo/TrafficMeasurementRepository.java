package eu.commean.backend.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import eu.commean.backend.data.TrafficCameraNode;
import eu.commean.backend.data.TrafficMeasurement;

public interface TrafficMeasurementRepository extends CrudRepository<TrafficMeasurement, Integer> {
	Iterable<TrafficMeasurement> findAllByTrafficCameraNode(TrafficCameraNode tcn);

	@Transactional
	Iterable<TrafficMeasurement> findAllByTimespan(@Param(value = "tcnId") int id, @Param(value = "timespan") String timeWindow);

	@Transactional
	@Modifying
	@Query(value = "SELECT public.create_hypertable(?1,?2)", nativeQuery = true)
	void createHypertable(String tableName, String timeColumn);

}
