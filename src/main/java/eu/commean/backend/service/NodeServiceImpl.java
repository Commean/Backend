package eu.commean.backend.service;

import eu.commean.backend.entity.Node;
import eu.commean.backend.repo.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class NodeServiceImpl implements NodeService {

	NodeRepository nodeRepository;

	@Autowired
	public NodeServiceImpl(NodeRepository nodeRepository) {
		this.nodeRepository = nodeRepository;
	}

	@Override
	public Node addNode(Node node) {
		return nodeRepository.save(node);
	}

	@Override
	public Node updateNode(Node node) {
		Node nodeFromDB = nodeRepository.findById(node.getId()).orElse(null);
		if (nodeFromDB != null) {
			if (!node.getName().equals("")) {
				nodeFromDB.setName(node.getName());
			}
			if (!node.getLocation().equals("POINT(0.0 0.0)")) {
				nodeFromDB.setLocation(node.getLocation());
			}
			if (!node.getTtnId().equals("")) {
				nodeFromDB.setTtnId(node.getTtnId());
			}
			nodeRepository.save(nodeFromDB);
			return nodeFromDB;
		}
		return null;
	}

	@Override
	@Transactional
	public List<Node> getAllNodes() {
		return (List<Node>) nodeRepository.findAll();
	}

	@Override
	@Transactional
	public Node getNodeById(UUID id) {
		return nodeRepository.findById(id).orElse(new Node(new UUID(0, 0), "null"));
	}

	@Override
	public List<Node> getAllNodesWhereLocationNotNull() {
		return (List<Node>) nodeRepository.findAllWhereLocationNotNull();
	}

	@Override
	public List<Node> getAllNodesWithTTNConnection() {
		return (List<Node>) nodeRepository.findByTtnIdIsNotNull();
	}

	@Override
	@Transactional
	public Node getNodeByTTNId(String ttnId) {
		Node node = nodeRepository.findByTtnId(ttnId).orElse(null);
		return node;
	}

	@Override
	public void deleteNodeById(UUID id) {
		nodeRepository.deleteById(id);

	}

}
