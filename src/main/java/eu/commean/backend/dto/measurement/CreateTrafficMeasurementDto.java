package eu.commean.backend.dto.measurement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTrafficMeasurementDto {
	private long timestamp;
	private int cars;
	private int trucks;
	private int bus;
	private int motorbike;
	private int averageTimeInPicture;

}
