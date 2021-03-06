package eu.commean.backend.controller;

import eu.commean.backend.dto.MapOverlayGeoJson;
import eu.commean.backend.dto.node.CreateNodeDto;
import eu.commean.backend.dto.node.NodeDto;
import eu.commean.backend.dto.node.NodeGeoJsonDto;
import eu.commean.backend.entity.Node;
import eu.commean.backend.service.NodeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/api/v1/nodes")
public class NodeController {

	private NodeService nodeService;


	@Autowired
	public NodeController(NodeService nodeService) {
		this.nodeService = nodeService;
	}

	@GetMapping(value = "/geojson", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public Object getAllNodes() {
		log.debug("NodeController:[GET] /geojson");

		List<NodeGeoJsonDto> data = nodeService.getAllNodesWhereLocationNotNull().stream()
				.map(node -> NodeGeoJsonDto.mapToDto(node, String.valueOf(10))).toList();
		MapOverlayGeoJson mapOverlayGeoJson = new MapOverlayGeoJson(data);

		if (data.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Nodes have been defined");

		return mapOverlayGeoJson;

	}

	@GetMapping(value = "", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public Object getNode(@RequestParam("id") UUID uuid) {
		log.debug("NodeController:[GET]");
		return NodeDto.convertToDto(nodeService.getNodeById(uuid));
	}

	// TODO: Implement Registration-Key
	@PostMapping(value = "", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createNode(@RequestBody CreateNodeDto nodeToCreate) {
		log.debug("NodeController:[POST]");
		log.debug("CreateNodeDto| Id: {}, RegKey: {}", nodeToCreate.getId(), nodeToCreate.getTtnId());
		nodeService.addNode(new Node(nodeToCreate.getId()));

		log.debug("NodeOnDB: {}", nodeService.getNodeById(nodeToCreate.getId()).getId());
	}

	@PutMapping(consumes = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public void updateNode(@RequestBody NodeDto nodeToUpdate) {
		log.debug("NodeController:[PUT], Node: {}", nodeToUpdate);
		nodeService.updateNode(NodeDto.convertToNode(nodeToUpdate));
	}

	//Exeptions
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Error in Json (Body)")
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void handleJsonParseExeption() {

	}


}
