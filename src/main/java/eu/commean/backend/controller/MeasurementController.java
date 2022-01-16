package eu.commean.backend.controller;

import eu.commean.backend.data.TrafficCameraNode;
import eu.commean.backend.data.TrafficMeasurement;
import eu.commean.backend.dto.measurement.CreateTrafficMeasurementDto;
import eu.commean.backend.dto.measurement.TrafficMeasurementStatisticsRealtimeDto;
import eu.commean.backend.service.TrafficCameraNodeService;
import eu.commean.backend.service.TrafficMeasurementService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Log4j2
@RestController
@RequestMapping("api/v1/measurements")
public class MeasurementController {

	@Autowired
	TrafficMeasurementService tms;
	@Autowired
	TrafficCameraNodeService tcns;

	@GetMapping(value = "/{node}/now")
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
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Traffic Camera Node with given id");

		} else {
			return TrafficMeasurementStatisticsRealtimeDto.convertToDto(tm);

		}
	}

	// TODO: Add API key check
	@PostMapping(path = "/{node}", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public void createNewMeasurement(@PathVariable("node") String id,
									 @RequestBody CreateTrafficMeasurementDto trafficMeasurement) {
		UUID uuid;
		if (id.matches("^[a-fA-F\\d]{8}(?:\\-[a-fA-F\\d]{4}){3}\\-[a-fA-F\\d]{12}$"))
			uuid = UUID.fromString(id);
		else
			uuid = new UUID(0, 0);

		log.debug("UUID: {}", uuid);

		TrafficMeasurement tm = new TrafficMeasurement(trafficMeasurement);
		TrafficCameraNode tcn = tcns.getTrafficCameraNodeById(uuid);
		tm.setTrafficCameraNode(tcn);

		log.debug("Measurement: {}, {}, {}|TrafficCameraNode: {}", tm.getId(), tm.getTrafficCameraNode(),
				tm.getTimestamp(), tcn.getId());

		tms.addTrafficMeasurement(tm);
	}


	// Exeptions
	@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Error in Json (Body)")
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public void handleJsonParseExeption() {
		//Nothing to do because error is handeld by Spring
	}

}
