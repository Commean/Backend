package eu.commean.backend.service;

import eu.commean.backend.data.Crossroad;

import java.util.List;
import java.util.UUID;

@Deprecated
public interface CrossroadService {

	Crossroad addCrossroad(Crossroad c);

	List<Crossroad> getAllCrossroadsLazy();
	
	List<Crossroad> getAllCrossroads();

	Crossroad getCrossroadById(UUID id);

	List<Crossroad> getCrossroadByCrossroadName(String name);

	void deleteCrossroadById(UUID id);
}
