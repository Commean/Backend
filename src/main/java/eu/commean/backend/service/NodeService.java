package eu.commean.backend.service;

import eu.commean.backend.entity.Node;
import eu.commean.backend.repo.NodeRepository;

import java.util.List;
import java.util.UUID;

/**
 * Node Service - Performs operations on Database via {@link NodeRepository TrafficCameraNodeRepository}, Entity: {@link Node}.
 *
 * @author Luca Nachbar
 **/
public interface NodeService {

	Node addNode(Node tcn);

	Node updateNode(Node tcn);

	List<Node> getAllNodes();

	Node getNodeById(UUID id);

	List<Node> getAllNodesWhereLocationNotNull();

	List<Node> getAllNodesWithTTNConnection();

	Node getNodeByTTNId(String ttnId);

	void deleteNodeById(UUID id);
}
