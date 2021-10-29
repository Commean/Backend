package eu.commean.backend.data;

import java.util.List;

import javax.persistence.*;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import lombok.*;

@Entity
@Table(name = "nodes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EnableAutoConfiguration
public class TrafficCameraNode {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NonNull
	String latitude;
	@NonNull
	String longitude;
	@NonNull
	@ManyToOne
	@JoinColumn(name = "crossroad_id",referencedColumnName = "id")
	private Crossroad crossroad;

	@OneToMany(mappedBy = "trafficCameraNode", cascade = CascadeType.ALL)
	private List<TrafficMeasurement> trafficMeasurement;
}
