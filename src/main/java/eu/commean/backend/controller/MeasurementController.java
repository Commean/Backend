package eu.commean.backend.controller;

import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eu.commean.backend.data.TrafficMeasurement;
import eu.commean.backend.dto.TrafficMeasurementStatisticsRealtimeDto;
import eu.commean.backend.service.TrafficMeasurementService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("api/v1/measurements")
public class MeasurementController {

	@Autowired
	TrafficMeasurementService tms;

	@GetMapping(value = "/{node}/now")
	@ResponseStatus(code = HttpStatus.OK)

	public Object getNewestMeasurementFromNode(@PathVariable("node") String id) {
		// TODO Change to UUID istead of ID
		UUID uuid;
		if (id.matches(" ^[0-9a-fA-F]{8}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{4}\\b-[0-9a-fA-F]{12}$ "))
			uuid = UUID.fromString(id);
		else
			uuid = new UUID(0, 0);
				
		TrafficMeasurement tm = tms.getLatestMeasurementFromId(uuid);
		if (tm == null) {
			log.error("No Measurements where found in the last minute for TCN with id: {}", uuid);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Traffic Camera Node with given id");

		}

		else {
			return TrafficMeasurementStatisticsRealtimeDto.convertToDto(tm);

		}
	}
	// TODO Make Endpoint: Create Measurement

}
