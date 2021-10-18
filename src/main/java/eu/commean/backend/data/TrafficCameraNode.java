package eu.commean.backend.data;

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
	@JoinColumn(name = "crossroad_id")
	private Crossroad crossroad;
}
