package eu.commean.backend.data;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "crossroads")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EnableAutoConfiguration
public class Crossroad {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;

	@NonNull
	String name;

	@OneToMany(mappedBy = "crossroad", cascade = CascadeType.ALL)
	private List<TrafficCameraNode> TrafficCameraNode;
}


