package eu.commean.backend.controller;

import eu.commean.backend.dto.MapOverlayGeoJson;
import eu.commean.backend.dto.node.CreateNodeDto;
import eu.commean.backend.dto.node.NodeDto;
import eu.commean.backend.dto.node.NodeGeoJsonDto;
import eu.commean.backend.entity.Node;
import eu.commean.backend.service.ApiKeyService;
import eu.commean.backend.service.NodeService;
import eu.commean.backend.service.TrafficMeasurementService;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("/api/v1/nodes")
public class NodeController {

	private NodeService tcns;
	private TrafficMeasurementService tms;
	private ModelMapper modelMapper;
	private ApiKeyService apiKeyService;

	@Autowired
	public NodeController(NodeService tcns, TrafficMeasurementService tms, ModelMapper modelMapper, ApiKeyService apiKeyService) {
		this.tcns = tcns;
		this.tms = tms;
		this.modelMapper = modelMapper;
		this.apiKeyService = apiKeyService;
	}

	@GetMapping(value = "/geojson", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public Object getAllNodes() {

		List<NodeGeoJsonDto> data = tcns.getAllNodesWhereLocationNotNull().stream()
				.map(node -> NodeGeoJsonDto.mapToDto(node, String.valueOf(10))).toList();
		MapOverlayGeoJson mapOverlayGeoJson = new MapOverlayGeoJson(data);

		if (data.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Nodes have been defined");

		return mapOverlayGeoJson;

	}

	@GetMapping(value = "", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public Object getNode(@RequestParam("id") UUID uuid) {
		return NodeDto.convertToDto(tcns.getNodeById(uuid));
	}

	// TODO: Implement Registration-Key and generation of API-Key
	@PostMapping(value = "", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Map<String, String> createNode(@RequestBody CreateNodeDto nodeToCreate) {
		log.debug("CreateNodeDto| Id: {}, RegKey: {}", nodeToCreate.getId(), nodeToCreate.getRegistrationKey());
		tcns.addNode(new Node(nodeToCreate.getId()));

		log.debug("NodeOnDB: {}", tcns.getNodeById(nodeToCreate.getId()).getId());
		Map<String,String> body = new HashMap<>();
		body.put("api-key",apiKeyService.generateApiKey().getKey());

		return body;

	}
	@PutMapping
	@ResponseStatus(code = HttpStatus.OK)
	public void updateNode(@RequestBody NodeDto nodeToUpdate){
		tcns.addNode(NodeDto.convertToTCN(nodeToUpdate));
	}

	//Exeptions
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Error in Json (Body)")
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void handleJsonParseExeption() {

	}


}
