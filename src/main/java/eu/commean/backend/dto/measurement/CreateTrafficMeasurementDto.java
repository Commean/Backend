package eu.commean.backend.dto.measurement;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTrafficMeasurementDto {
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Timestamp timestamp;
	private int cars;
	private int trucks;
	private int averageTimeInPicture;

}
