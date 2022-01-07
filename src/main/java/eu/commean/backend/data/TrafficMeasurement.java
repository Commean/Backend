package eu.commean.backend.data;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;

import org.hibernate.annotations.GenericGenerator;
import org.postgresql.util.PGTimestamp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(name = "TrafficMeasurement.findAllByTimespan", query = "SELECT * FROM traffic_measurement tm WHERE tm.timestamp > now() - make_interval(0,0,0,:days) AND trafficcameranode_id = :id", resultClass = TrafficMeasurement.class)
@NamedNativeQuery(name = "TrafficMeasurement.findLatestById", query = "SELECT * FROM traffic_measurement tm WHERE trafficcameranode_id = :id ORDER BY tm.timestamp DESC LIMIT 1", resultClass = TrafficMeasurement.class)

//TODO Implement function to convert PostgreSQL Table to Hypertable from TimeScaleDB on first start
public class TrafficMeasurement {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	private UUID id;

	private int trucks;

	private int cars;

	private int averageTimeInPicture;

	@NonNull
	private Timestamp timestamp;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "trafficcameranode_id", referencedColumnName = "id")
	private TrafficCameraNode trafficCameraNode;

	public TrafficMeasurement(int trucks, int cars, int averageTimeInPicture, @NonNull Timestamp timestamp,
			@NonNull TrafficCameraNode trafficCameraNode) {
		super();
		this.trucks = trucks;
		this.cars = cars;
		this.averageTimeInPicture = averageTimeInPicture;
		this.timestamp = timestamp;
		this.trafficCameraNode = trafficCameraNode;
	}

	@Override
	public int hashCode() {
		return trucks * cars * averageTimeInPicture;
	}

	public UUID getId() {
		return id;
	}

	public int getTrucks() {
		return trucks;
	}

	public int getCars() {
		return cars;
	}

	public int getAverageTimeInPicture() {
		return averageTimeInPicture;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public TrafficCameraNode getTrafficCameraNode() {
		return trafficCameraNode;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setTrucks(int trucks) {
		this.trucks = trucks;
	}

	public void setCars(int cars) {
		this.cars = cars;
	}

	public void setAverageTimeInPicture(int averageTimeInPicture) {
		this.averageTimeInPicture = averageTimeInPicture;
	}

	public void setTimestamp(PGTimestamp timestamp) {
		this.timestamp = timestamp;
	}

	public void setTrafficCameraNode(TrafficCameraNode trafficCameraNode) {
		this.trafficCameraNode = trafficCameraNode;
	}

}
