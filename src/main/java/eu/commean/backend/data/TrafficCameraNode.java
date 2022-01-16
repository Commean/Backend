package eu.commean.backend.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "nodes")

@ToString
@NoArgsConstructor
@AllArgsConstructor
@NamedNativeQuery(name = "TrafficCameraNode.findAllWhereLocationNotNull", query = "SELECT * FROM nodes n WHERE n.\"location\" NOTNULL;", resultClass = TrafficCameraNode.class)
@EnableAutoConfiguration
public class TrafficCameraNode {
	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
	private UUID id;
	private String location;
	@ManyToOne
	@ToStringExclude
	@JoinColumn(name = "crossroad_id", referencedColumnName = "id")
	private Crossroad crossroad;

	@ToStringExclude
	@OneToMany(mappedBy = "trafficCameraNode")
	private List<TrafficMeasurement> trafficMeasurement;

	@Override
	public int hashCode() {
		return id.hashCode() * location.hashCode();
	}

	public UUID getId() {
		return id;
	}

	public String getLocation() {
		return location;
	}

	public Crossroad getCrossroad() {
		return crossroad;
	}

	public List<TrafficMeasurement> getTrafficMeasurement() {
		return trafficMeasurement;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setCrossroad(Crossroad crossroad) {
		this.crossroad = crossroad;
	}

	public void setTrafficMeasurement(List<TrafficMeasurement> trafficMeasurement) {
		this.trafficMeasurement = trafficMeasurement;
	}

	public TrafficCameraNode(UUID id) {
		this.id = id;
	}

	public TrafficCameraNode(String location) {
		this.location = location;
	}

	public TrafficCameraNode(UUID id, String location) {
		this.id = id;
		this.location = location;
	}
}
