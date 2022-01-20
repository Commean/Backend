package eu.commean.backend.data;

import lombok.*;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "crossroads")
@NamedQueries({
		@NamedQuery(name = "Crossroad.existsByTrafficCameraNode_Id", query = "select (count(c) > 0) from Crossroad c left join c.trafficCameraNode trafficCameraNode where trafficCameraNode.id = :id")
})

@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EnableAutoConfiguration

@Deprecated
public class Crossroad {

	@NonNull
	String crossroadName;
	@Id
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	private UUID id;
	@NonNull
	private String crossroadLocation;

	@ToStringExclude
	@OneToMany(mappedBy = "crossroad", cascade = CascadeType.ALL)
	private List<TrafficCameraNode> trafficCameraNode;

	public Crossroad(UUID id, @NonNull String crossroadName, @NonNull String crossroadLocation) {
		this.id = id;
		this.crossroadName = crossroadName;
		this.crossroadLocation = crossroadLocation;
	}

	@Override
	public int hashCode() {
		return id.hashCode() * crossroadName.hashCode() * crossroadLocation.hashCode();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getCrossroadName() {
		return crossroadName;
	}

	public void setCrossroadName(String crossroadName) {
		this.crossroadName = crossroadName;
	}

	public String getCrossroadLocation() {
		return crossroadLocation;
	}

	public void setCrossroadLocation(String crossroadLocation) {
		this.crossroadLocation = crossroadLocation;
	}

	public List<TrafficCameraNode> getTrafficCameraNode() {
		return trafficCameraNode;
	}

	public void setTrafficCameraNode(List<TrafficCameraNode> trafficCameraNode) {
		this.trafficCameraNode = trafficCameraNode;
	}
}
