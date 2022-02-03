package eu.commean.backend.dto.node;

import eu.commean.backend.data.TrafficCameraNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NodeDto {

	private UUID id = new UUID(0, 0);
	private String name = "";
	private double[] location = {0, 0};

	public NodeDto(UUID id, double[] parseLocation) {
	}

	public static NodeDto convertToDto(TrafficCameraNode tcn) {

		return new NodeDto(tcn.getId(), tcn.getName(), parseLocation(tcn.getLocation()));

	}

	private static double[] parseLocation(String location) {
		double[] output = {0, 0};
		Pattern pattern = Pattern.compile("-?[\\d]+.[\\d]+ -?[\\d]+.[\\d]+");
		Matcher matcher = pattern.matcher(location);
		if (matcher.find()) {
			String[] tmp = matcher.group().split(" ");
			for (int i = 0; i < tmp.length; i++) {
				output[i] = Double.parseDouble(tmp[i]);
			}
			return output;
		}
		return new double[]{1.0, 10.};


	}

}