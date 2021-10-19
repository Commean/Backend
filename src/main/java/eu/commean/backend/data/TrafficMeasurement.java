package eu.commean.backend.data;

import java.time.Instant;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

@Measurement(name = "trafficMeasurement")
public class TrafficMeasurement {

	@Column(tag = true)
	final private int id;
	@Column
	final private int truckIn;
	@Column
	final private int truckOut;
	@Column
	final private int carIn;
	@Column
	final private int carOut;
	@Column
	final private int averageTimeInPicture;
	@Column(timestamp = true)
	final Instant timestamp = Instant.now();

}
