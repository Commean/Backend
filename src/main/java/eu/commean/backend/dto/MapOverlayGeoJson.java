package eu.commean.backend.dto;

import ch.cordsen.geojson.annotation.GeoJson;
import ch.cordsen.geojson.annotation.GeoJsonFeatures;
import ch.cordsen.geojson.serializer.GeoJsonSerializer;
import ch.cordsen.geojson.serializer.GeoJsonType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eu.commean.backend.dto.node.NodeGeoJsonDto;
import lombok.Data;

import java.util.List;

@GeoJson(type = GeoJsonType.FEATURE_COLLECTION)
@JsonSerialize(using = GeoJsonSerializer.class)
@Data
public class MapOverlayGeoJson {


	@GeoJsonFeatures
	private List<NodeGeoJsonDto> trafficCameraNodes;

	@Override
	public String toString() {
		return super.toString();
	}

	public MapOverlayGeoJson(List<NodeGeoJsonDto> trafficCameraNodes) {
		this.trafficCameraNodes = trafficCameraNodes;
	}

}
