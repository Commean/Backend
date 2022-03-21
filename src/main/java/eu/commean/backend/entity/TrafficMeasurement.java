package eu.commean.backend.entity;

import eu.commean.backend.pojo.mqtt.Payload;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;
import org.postgresql.util.PGTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
@IdClass(MeasurementId.class)
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(name = "TrafficMeasurement.findAllByTimespan", query = "SELECT * FROM traffic_measurement tm WHERE tm.timestamp > now() - make_interval(0,0,0,:days,:hours, :minutes,:seconds) AND tm.node_id = :id", resultClass = TrafficMeasurement.class)
@NamedNativeQuery(name = "TrafficMeasurement.findLatestById", query = "SELECT * FROM traffic_measurement tm WHERE tm.node_id = :id ORDER BY tm.timestamp DESC LIMIT 1", resultClass = TrafficMeasurement.class)
@NamedNativeQuery(name = "TrafficMeasurement.createHypertable", query = "SELECT CREATE_HYPERTABLE('traffic_measurement','timestamp',migrate_data => TRUE);")
//TODO Implement function to convert PostgreSQL Table to Hypertable from TimeScaleDB on first start
public class TrafficMeasurement {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
	private UUID id;

	private int trucks;

	private int cars;

	private int bus;

	private int motorbike;


	private int averageTimeInPicture;

	@Id
	@NonNull
	private Timestamp timestamp;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "node_id", referencedColumnName = "id")
	private Node node;

	public TrafficMeasurement(int trucks, int cars, int bus, int motorbike, int averageTimeInPicture, @NonNull Timestamp timestamp) {
		this.trucks = trucks;
		this.cars = cars;
		this.bus = bus;
		this.motorbike = motorbike;
		this.averageTimeInPicture = averageTimeInPicture;
		this.timestamp = timestamp;
	}

	public TrafficMeasurement(Payload payload) {
		this.trucks = payload.getCount().getTruck();
		this.cars = payload.getCount().getCar();
		this.bus = payload.getCount().getBus();
		this.motorbike = payload.getCount().getMotorbike();
		this.timestamp = Timestamp.from(Instant.ofEpochSecond(Math.round(payload.getTime())));
		this.averageTimeInPicture = payload.getAtip();
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

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Node getNode() {
		return node;
	}

	public void setNode(Node node) {
		this.node = node;
	}

	public int getBus() {
		return bus;
	}

	public void setBus(int bus) {
		this.bus = bus;
	}

	public int getMotorbike() {
		return motorbike;
	}

	public void setMotorbike(int motorbike) {
		this.motorbike = motorbike;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		TrafficMeasurement that = (TrafficMeasurement) o;
		return trucks == that.trucks && cars == that.cars && bus == that.bus && motorbike == that.motorbike && averageTimeInPicture == that.averageTimeInPicture && Objects.equals(id, that.id) && timestamp.equals(that.timestamp) && node.equals(that.node);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, trucks, cars, bus, motorbike, averageTimeInPicture, timestamp, node);
	}

}
