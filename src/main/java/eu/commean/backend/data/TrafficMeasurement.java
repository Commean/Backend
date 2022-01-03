package eu.commean.backend.data;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;

import org.postgresql.util.PGTimestamp;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(name = "TrafficMeasurement.findAllByTimespan", query = "SELECT * FROM traffic_measurement tm WHERE tm.timestamp > now() - make_interval(0,0,0,:days) AND trafficcameranode_id = :id", resultClass = TrafficMeasurement.class)
@NamedNativeQuery(name = "TrafficMeasurement.findLatestById", query = "SELECT * FROM traffic_measurement tm WHERE trafficcameranode_id = :id ORDER BY tm.timestamp DESC LIMIT 1",resultClass = TrafficMeasurement.class)

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
	private Timestamp timestamp;

	@NonNull
	@ManyToOne
	@JoinColumn(name = "trafficcameranode_id", referencedColumnName = "id")
	private TrafficCameraNode trafficCameraNode;

	public TrafficMeasurement(int truckIn, int truckOut, int carIn, int carOut, int averageTimeInPicture,
			@NonNull Timestamp timestamp, @NonNull TrafficCameraNode trafficCameraNode) {
		super();
		this.truckIn = truckIn;
		this.truckOut = truckOut;
		this.carIn = carIn;
		this.carOut = carOut;
		this.averageTimeInPicture = averageTimeInPicture;
		this.timestamp = timestamp;
		this.trafficCameraNode = trafficCameraNode;
	}

	@Override
	public int hashCode() {
		return truckIn * carOut * carIn * averageTimeInPicture * truckOut;
	}

	public int getId() {
		return id;
	}

	public int getTruckIn() {
		return truckIn;
	}

	public int getTruckOut() {
		return truckOut;
	}

	public int getCarIn() {
		return carIn;
	}

	public int getCarOut() {
		return carOut;
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

	public void setId(int id) {
		this.id = id;
	}

	public void setTruckIn(int truckIn) {
		this.truckIn = truckIn;
	}

	public void setTruckOut(int truckOut) {
		this.truckOut = truckOut;
	}

	public void setCarIn(int carIn) {
		this.carIn = carIn;
	}

	public void setCarOut(int carOut) {
		this.carOut = carOut;
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
