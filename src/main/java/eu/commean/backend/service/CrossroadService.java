package eu.commean.backend.service;

import java.util.List;
import java.util.UUID;

import eu.commean.backend.data.Crossroad;

public interface CrossroadService {

	Crossroad addCrossroad(Crossroad c);

	List<Crossroad> getAllCrossroadsLazy();
	
	List<Crossroad> getAllCrossroads();

	Crossroad getCrossroadById(UUID id);

	List<Crossroad> getCrossroadByCrossroadName(String name);

	void deleteCrossroadById(UUID id);
}
