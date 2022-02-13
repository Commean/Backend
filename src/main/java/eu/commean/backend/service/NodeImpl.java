package eu.commean.backend.service;

import eu.commean.backend.entity.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NodeImpl implements NodeService {

	eu.commean.backend.repo.NodeRepository NodeRepository;

	@Autowired
	public NodeImpl(eu.commean.backend.repo.NodeRepository nodeRepository) {
		this.NodeRepository = nodeRepository;
	}

	@Override
	public Node addNode(Node tcn) {
		return NodeRepository.save(tcn);
	}

	@Override
	@Transactional
	public List<Node> getAllNodes() {
		return (List<Node>) NodeRepository.findAll();
	}

	@Override
	@Transactional
	public Node getNodeById(UUID id) {
		return NodeRepository.findById(id).orElse(null);
	}

	@Override
	public Optional<Node> getNodesByName(String name) {
		return NodeRepository.findByNameLike(name);
	}


	@Override
	public List<Node> getAllNodesWhereLocationNotNull() {
		return (List<Node>) NodeRepository.findAllWhereLocationNotNull();
	}

	@Override
	public void deleteNodeById(UUID id) {
		NodeRepository.deleteById(id);

	}

}
