package eu.commean.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.commean.backend.data.Crossroad;
import eu.commean.backend.repo.CrossroadRepository;

@Service
public class CrossroadServiceImpl implements CrossroadService {

	CrossroadRepository crossroadRepository;

	@Autowired
	public CrossroadServiceImpl(CrossroadRepository crossroadRepository) {
		this.crossroadRepository = crossroadRepository;
	}

	@Override
	public Crossroad addCrossroad(Crossroad c) {
		return crossroadRepository.save(c);
	}

	@Override
	@Transactional
	public List<Crossroad> getAllCrossroads() {
		return (List<Crossroad>) crossroadRepository.findAll();
	}

	@Override
	@Transactional
	public Crossroad getCrossroadById(int id) {
		Crossroad c = crossroadRepository.findById(id).orElse(null);
		return c;
	}

	@Override
	@Transactional
	public List<Crossroad> getCrossroadByCrossroadName(String name) {
		List<Crossroad> crossroads = crossroadRepository.findByCrossroadName(name);
		return crossroads;
	}

	@Override
	public void deleteCrossroadById(int id) {
		crossroadRepository.deleteById(id);

	}

}
