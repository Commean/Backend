package eu.commean.backend.controller;

import eu.commean.backend.dto.measurement.CreateTrafficMeasurementDto;
import eu.commean.backend.dto.measurement.MeasurementPopupDto;
import eu.commean.backend.entity.Node;
import eu.commean.backend.entity.TrafficMeasurement;
import eu.commean.backend.security.jwt.JwtProvider;
import eu.commean.backend.service.NodeService;
import eu.commean.backend.service.TrafficMeasurementService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("api/v1/measurements")
public class MeasurementController {


	TrafficMeasurementService tms;
	NodeService nodeService;
	JwtProvider jwtProvider;

	@Autowired
	public MeasurementController(TrafficMeasurementService tms, NodeService nodeService, JwtProvider jwtProvider) {
		this.tms = tms;
		this.nodeService = nodeService;
		this.jwtProvider = jwtProvider;
	}

	@GetMapping(value = "/{node}/now", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)

	public Object getNewestMeasurementFromNode(@PathVariable("node") String id) {
		UUID uuid;
		if (id.matches("[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}"))
			uuid = UUID.fromString(id);
		else
			uuid = new UUID(0, 0);

		TrafficMeasurement tm = tms.getLatestMeasurementFromId(uuid);
		if (tm == null) {
			log.error("No Measurements where found in the last minute for TCN with id: {}", uuid);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Node with given id");

		} else {
			return MeasurementPopupDto.convertToDto(tm, nodeService.getNodeById(tm.getNode().getId()));

		}
	}

	@PostMapping(path = "", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createNewMeasurement(@RequestHeader(name = "Authorization") String token, CreateTrafficMeasurementDto trafficMeasurementDto) {
		UUID uuid;
		token = token.substring(7);
		log.debug("Token {}", token);
		String token_id = jwtProvider.getUsernameFromJWT(token);
		log.debug("Id {}", token_id);

		if (token_id.matches("^[a-fA-F\\d]{8}(?:\\-[a-fA-F\\d]{4}){3}\\-[a-fA-F\\d]{12}$"))
			uuid = UUID.fromString(token_id);
		else
			uuid = new UUID(0, 0);

		log.debug("UUID: {}", uuid);
		if (!uuid.equals(new UUID(0, 0))) {
			TrafficMeasurement tm = new TrafficMeasurement(trafficMeasurementDto);
			log.debug("Measurement: {}", trafficMeasurementDto);
			if (trafficMeasurementDto.getTimestamp() == 0)
				trafficMeasurementDto.setTimestamp(Instant.now().getEpochSecond());
			Node tcn = nodeService.getNodeById(uuid);
			tm.setNode(tcn);

			log.debug("Measurement: {}, {}, {}|TrafficCameraNode: {}", tm.getId(), tm.getNode(),
					tm.getTimestamp(), tcn.getId());

			tms.addTrafficMeasurement(tm);
		} else {
			log.warn("{} has no access to create Measurements", token_id);
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "The given JWT cannot create measurements");
		}
	}


	// Exceptions
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Error in Json (Body)")
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void handleJsonParseException() {
		//Nothing to do because error is handled by Spring
	}

}
