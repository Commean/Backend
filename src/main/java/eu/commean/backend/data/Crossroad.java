package eu.commean.backend.data;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "crossroads")

@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EnableAutoConfiguration

public class Crossroad {

	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	private UUID id;

	@NonNull
	String crossroadName;

	@NonNull
	private String crossroadLocation;

	@ToStringExclude
	@OneToMany(mappedBy = "crossroad", cascade = CascadeType.ALL)
	private List<TrafficCameraNode> trafficCameraNode;

	@Override
	public int hashCode() {
		return id.hashCode() * crossroadName.hashCode() * crossroadLocation.hashCode();
	}

	public UUID getId() {
		return id;
	}

	public String getCrossroadName() {
		return crossroadName;
	}

	public String getCrossroadLocation() {
		return crossroadLocation;
	}

	public List<TrafficCameraNode> getTrafficCameraNode() {
		return trafficCameraNode;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setCrossroadName(String crossroadName) {
		this.crossroadName = crossroadName;
	}

	public void setCrossroadLocation(String crossroadLocation) {
		this.crossroadLocation = crossroadLocation;
	}

	public void setTrafficCameraNode(List<TrafficCameraNode> trafficCameraNode) {
		this.trafficCameraNode = trafficCameraNode;
	}
}
