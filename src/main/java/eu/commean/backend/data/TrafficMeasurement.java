package eu.commean.backend.data;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
//TODO Implement function to convert PostgreSQL Table to Hypertable from TimeScaleDB on first start
public class TrafficMeasurement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int truckIn;

	private int truckOut;

	private int carIn;

	private int carOut;

	private int averageTimeInPicture;

	@NonNull
	private Instant timestamp;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "trafficcameranode_id",referencedColumnName = "id")
	private TrafficCameraNode trafficCameraNode;

}
