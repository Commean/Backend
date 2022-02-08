package eu.commean.backend.entity;

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
	private String name;

	@ToStringExclude
	@OneToMany(mappedBy = "trafficCameraNode")
	private List<TrafficMeasurement> trafficMeasurement;

	public TrafficCameraNode(UUID id) {
		this.id = id;
	}

	public TrafficCameraNode(UUID id, String location) {
		this.id = id;
		this.location = location;
	}

	public TrafficCameraNode(UUID id, String location, String name) {
		this.id = id;
		this.location = location;
		this.name = name;
	}

	@Override
	public int hashCode() {
		return id.hashCode() * location.hashCode() * name.hashCode();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TrafficMeasurement> getTrafficMeasurement() {
		return trafficMeasurement;
	}

	public void setTrafficMeasurement(List<TrafficMeasurement> trafficMeasurement) {
		this.trafficMeasurement = trafficMeasurement;
	}
}
