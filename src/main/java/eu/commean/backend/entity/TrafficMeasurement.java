package eu.commean.backend.entity;

import eu.commean.backend.dto.measurement.CreateTrafficMeasurementDto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.postgresql.util.PGTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@IdClass(MeasurementId.class)
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(name = "TrafficMeasurement.findAllByTimespan", query = "SELECT * FROM traffic_measurement tm WHERE tm.timestamp > now() - make_interval(0,0,0,:days,:hours, :minutes,:seconds) AND tm.node_id = :id", resultClass = TrafficMeasurement.class)
@NamedNativeQuery(name = "TrafficMeasurement.findLatestById", query = "SELECT * FROM traffic_measurement tm WHERE tm.node_id = :id ORDER BY tm.timestamp DESC LIMIT 1", resultClass = TrafficMeasurement.class)

//TODO Implement function to convert PostgreSQL Table to Hypertable from TimeScaleDB on first start
public class TrafficMeasurement {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
	private UUID id;

	private int trucks;

	private int cars;

	private int averageTimeInPicture;

	@Id
	@NonNull
	private Timestamp timestamp;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "node_id", referencedColumnName = "id")
	private Node node;

	public TrafficMeasurement(CreateTrafficMeasurementDto trafficMeasurement) {

		this.averageTimeInPicture = trafficMeasurement.getAverageTimeInPicture();
		this.cars = trafficMeasurement.getCars();
		this.trucks = trafficMeasurement.getTrucks();
		this.timestamp = Timestamp.from(Instant.ofEpochSecond(trafficMeasurement.getTimestamp()));
	}

	@Override
	public int hashCode() {
		return trucks * cars * averageTimeInPicture;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public int getTrucks() {
		return trucks;
	}

	public void setTrucks(int trucks) {
		this.trucks = trucks;
	}

	public int getCars() {
		return cars;
	}

	public void setCars(int cars) {
		this.cars = cars;
	}

	public int getAverageTimeInPicture() {
		return averageTimeInPicture;
	}

	public void setAverageTimeInPicture(int averageTimeInPicture) {
		this.averageTimeInPicture = averageTimeInPicture;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(PGTimestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

}
