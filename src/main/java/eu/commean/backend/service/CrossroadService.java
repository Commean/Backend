package eu.commean.backend.service;

import java.util.List;

import eu.commean.backend.data.Crossroad;

public interface CrossroadService {

	Crossroad addCrossroad(Crossroad c);

	List<Crossroad> getAllCrossroads();

	Crossroad getCrossroadById(int id);

	List<Crossroad> getCrossroadByName(String name);

	void deleteCrossroadById(int id);	
}
