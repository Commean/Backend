package eu.commean.backend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
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
		List<Crossroad> c = crossroadRepository.findAll();
		Hibernate.initialize(c);
		return c;
	}

	@Override
	@Transactional
	public Crossroad getCrossroadById(int id) {
		return crossroadRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public List<Crossroad> getCrossroadByCrossroadName(String name) {
		return crossroadRepository.findByCrossroadName(name);
	}

	@Override
	public void deleteCrossroadById(int id) {
		crossroadRepository.deleteById(id);

	}

	@Override
	public List<Crossroad> getAllCrossroadsLazy() {
		return crossroadRepository.findAll();
	}

}
