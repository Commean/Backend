package eu.commean.backend.data;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.geo.Point;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NonNull
	private Point location;
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
		return id * location.hashCode();
	}

	public Integer getId() {
		return id;
	}

	public Point getLocation() {
		return location;
	}

	public Crossroad getCrossroad() {
		return crossroad;
	}

	public List<TrafficMeasurement> getTrafficMeasurement() {
		return trafficMeasurement;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public void setCrossroad(Crossroad crossroad) {
		this.crossroad = crossroad;
	}

	public void setTrafficMeasurement(List<TrafficMeasurement> trafficMeasurement) {
		this.trafficMeasurement = trafficMeasurement;
	}
}
