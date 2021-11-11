package eu.commean.backend.data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.geo.Point;

import ch.cordsen.geojson.annotation.GeoJson;
import ch.cordsen.geojson.annotation.GeoJsonGeometry;
import ch.cordsen.geojson.annotation.GeoJsonId;
import ch.cordsen.geojson.annotation.GeoJsonProperties;
import ch.cordsen.geojson.serializer.GeoJsonType;
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
@GeoJson(type = GeoJsonType.FEATURE)
public class Crossroad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeoJsonId
	private Integer id;
	@NonNull
	@GeoJsonProperties
	String crossroadName;
	@NonNull
	@GeoJsonGeometry
	private Point crossroadLocation;

	@ToStringExclude
	@OneToMany(mappedBy = "crossroad",cascade = CascadeType.ALL)
	private List<TrafficCameraNode> trafficCameraNode;

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id * crossroadName.hashCode() * crossroadLocation.hashCode();
	}

	public Integer getId() {
		return id;
	}

	public String getCrossroadName() {
		return crossroadName;
	}

	public Point getCrossroadLocation() {
		return crossroadLocation;
	}
	

	public List<TrafficCameraNode> getTrafficCameraNode() {
		return trafficCameraNode;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCrossroadName(String crossroadName) {
		this.crossroadName = crossroadName;
	}

	public void setCrossroadLocation(Point crossroadLocation) {
		this.crossroadLocation = crossroadLocation;
	}

	public void setTrafficCameraNode(List<TrafficCameraNode> trafficCameraNode) {
		this.trafficCameraNode = trafficCameraNode;
	}
}
