package eu.commean.backend.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
public class TrafficCameraNodeController {

	@Autowired
	TrafficMeasurementService tms;

	@GetMapping(value = "/crossroad")
	@ResponseStatus(code = HttpStatus.OK)


	public Object getNewestMeasurementFromNode(@RequestParam Map<String, String> params) {
		// TODO Change to UUID istead of ID

		String untrustedId = params.get("id");

		if (untrustedId.matches("^[1-9]\\d*$")) {

			int id = Integer.parseInt(untrustedId);

			TrafficMeasurement tm = tms.getLatestMeasurementFromId(id);
			if (tm == null)
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Traffic Camera Node with given id");
			else {
				return TrafficMeasurementStatisticsRealtimeDto.convertToDto(tm);

			}
		} else
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid ID-format");

	}

}
