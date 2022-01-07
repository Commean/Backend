package eu.commean.backend.data;

import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "nodes")

@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EnableAutoConfiguration
public class TrafficCameraNode {
	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	private UUID id;
	@NonNull
	private String location;
	@NonNull
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
}
