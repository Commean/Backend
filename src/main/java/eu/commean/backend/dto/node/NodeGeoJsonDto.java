package eu.commean.backend.dto.node;


import ch.cordsen.geojson.annotation.GeoJson;
import ch.cordsen.geojson.annotation.GeoJsonGeometry;
import ch.cordsen.geojson.annotation.GeoJsonId;
import ch.cordsen.geojson.annotation.GeoJsonProperty;
import ch.cordsen.geojson.serializer.GeoJsonSerializer;
import ch.cordsen.geojson.serializer.GeoJsonType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import eu.commean.backend.entity.Node;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;

import java.util.UUID;

@Data
@AllArgsConstructor
@GeoJson(type = GeoJsonType.FEATURE)
@JsonSerialize(using = GeoJsonSerializer.class)
public class NodeGeoJsonDto {

	private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory(new PrecisionModel(), 4326);
	private static final WKTReader WKT_READER = new WKTReader(GEOMETRY_FACTORY);


	@GeoJsonId
	private UUID id;

	private String location;

	@GeoJsonProperty
	private String traffic;

	@GeoJsonProperty
	private String name;

	public static NodeGeoJsonDto mapToDto(Node tn, String traffic) {

		return new NodeGeoJsonDto(tn.getId(), tn.getLocation(), traffic, tn.getName());

	}

	@GeoJsonGeometry
	public Geometry getLocationPoint() throws ParseException {
		return WKT_READER.read(location);

	}

}
