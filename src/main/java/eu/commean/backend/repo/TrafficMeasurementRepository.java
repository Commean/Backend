package eu.commean.backend.repo;

import org.springframework.data.repository.CrudRepository;

import eu.commean.backend.data.TrafficMeasurement;

public interface TrafficMeasurementRepository extends CrudRepository<TrafficMeasurement,Integer>{
	//TODO add custom querries for time ranges
}
