package eu.commean.backend.dto.node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateNodeDto {

	private UUID id;
	private String registrationKey;


}
