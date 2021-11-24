package eu.commean.backend.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.cordsen.geojson.annotation.GeoJson;
import ch.cordsen.geojson.annotation.GeoJsonFeatures;
import ch.cordsen.geojson.serializer.GeoJsonSerializer;
import ch.cordsen.geojson.serializer.GeoJsonType;
import eu.commean.backend.data.Crossroad;
import lombok.Data;

@GeoJson(type = GeoJsonType.FEATURE_COLLECTION)
@JsonSerialize(using = GeoJsonSerializer.class)
@Data
public class MapOverlayGeoJson {
	
	
	public MapOverlayGeoJson(List<CrossroadDto> crossroads) {
		this.crossroads = crossroads;
	}

	@Override
	public String toString() {
		return super.toString();
	}

	@GeoJsonFeatures
	private List<CrossroadDto> crossroads;

}
