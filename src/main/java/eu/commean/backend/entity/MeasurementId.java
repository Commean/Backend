package eu.commean.backend.entity;

import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
public class MeasurementId implements Serializable {

	private UUID id;

	private Timestamp timestamp;

	public MeasurementId(UUID id, Timestamp timestamp) {
		this.id = id;
		this.timestamp = timestamp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MeasurementId that = (MeasurementId) o;
		return id.equals(that.id) && timestamp.equals(that.timestamp);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, timestamp);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
}
