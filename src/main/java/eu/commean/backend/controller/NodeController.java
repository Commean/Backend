package eu.commean.backend.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eu.commean.backend.dto.CrossroadDto;
import eu.commean.backend.dto.MapOverlayGeoJson;
import eu.commean.backend.service.CrossroadService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1/nodes")
public class NodeController {

	@Autowired
	private CrossroadService cs;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(value = "")
	@ResponseStatus(code = HttpStatus.OK)
	public Object getAllCrossroads() {

		List<CrossroadDto> data = cs.getAllCrossroads().stream()
				.map(crossroad -> modelMapper.map(crossroad, CrossroadDto.class)).toList();
		MapOverlayGeoJson mogj = new MapOverlayGeoJson(data);

		if (data.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Crossroad have been defined");

		return mogj;

	}

}
