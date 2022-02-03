package eu.commean.backend.repo;

import eu.commean.backend.entity.TrafficCameraNode;
import eu.commean.backend.entity.TrafficMeasurement;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

public interface TrafficMeasurementRepository extends CrudRepository<TrafficMeasurement, UUID> {
	Iterable<TrafficMeasurement> findAllByTrafficCameraNode(TrafficCameraNode tcn);

	@Transactional
	Iterable<TrafficMeasurement> findAllByTimespan(@Param(value = "id") UUID uuid, @Param(value = "days") int days, @Param(value = "hours") int hours, @Param(value = "minutes") int minutes, @Param(value = "seconds") int seconds);

	Optional<TrafficMeasurement> findLatestById(@Param(value = "id") UUID id);

	@Transactional
	@Modifying
	@Query(value = "SELECT public.create_hypertable(?1,?2)", nativeQuery = true)
	void createHypertable(String tableName, String timeColumn);

}
