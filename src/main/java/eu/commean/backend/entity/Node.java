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
@NamedNativeQuery(name = "Node.findAllWhereLocationNotNull", query = "SELECT * FROM nodes n WHERE n.\"location\" NOTNULL;", resultClass = Node.class)
@EnableAutoConfiguration
public class Node {
	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	//@GeneratedValue(strategy = GenerationType.AUTO, generator = "uuid2")
	private UUID id;
	private String location;
	private String name;
	@Column(name = "ttn_id")
	private String ttnId;

	@ToStringExclude
	@OneToMany(mappedBy = "node")
	private List<TrafficMeasurement> trafficMeasurement;

	public Node(UUID id) {
		this.id = id;
	}

	public Node(UUID id, String location) {
		this.id = id;
		this.location = location;
	}

	public Node(UUID id, String location, String name, String ttnId) {
		this.id = id;
		this.location = location;
		this.name = name;
		this.ttnId = ttnId;
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

	public String getTtnId() {
		return ttnId;
	}

	public void setTtnId(String ttnId) {
		this.ttnId = ttnId;
	}
}
