package eu.commean.backend;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eu.commean.backend.dto.CrossroadDto;
import eu.commean.backend.dto.MapOverlayGeoJson;
import eu.commean.backend.exception.NoDataFoundException;
import eu.commean.backend.exception.NoDataFoundResponse;
import eu.commean.backend.service.CrossroadService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/api/nodes")
public class BackendController {

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
			throw new NoDataFoundException("No Crossroad have been defined", 404);

		return mogj;

	}

	@ExceptionHandler(NoDataFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ResponseBody
	public Object NoCrossroads(NoDataFoundException nde) {
		log.error("No Crossroad have been defined");
		return new NoDataFoundResponse(nde.getReason(), nde.getError_code());

	}

}
