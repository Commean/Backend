
package eu.commean.backend.pojo.mqtt.ttn;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data_rate",
        "coding_rate",
        "frequency",
        "timestamp",
        "time"
})
@Generated("jsonschema2pojo")
public class Settings {

    @JsonProperty("data_rate")
    private DataRate dataRate;
    @JsonProperty("coding_rate")
    private String codingRate;
    @JsonProperty("frequency")
    private String frequency;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("time")
    private String time;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("data_rate")
    public DataRate getDataRate() {
        return dataRate;
    }

    @JsonProperty("data_rate")
    public void setDataRate(DataRate dataRate) {
        this.dataRate = dataRate;
    }

    @JsonProperty("coding_rate")
    public String getCodingRate() {
        return codingRate;
    }

    @JsonProperty("coding_rate")
    public void setCodingRate(String codingRate) {
        this.codingRate = codingRate;
    }

    @JsonProperty("frequency")
    public String getFrequency() {
        return frequency;
    }

    @JsonProperty("frequency")
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @JsonProperty("timestamp")
    public Long getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Settings.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("dataRate");
        sb.append('=');
        sb.append(((this.dataRate == null) ? "<null>" : this.dataRate));
        sb.append(',');
        sb.append("codingRate");
        sb.append('=');
        sb.append(((this.codingRate == null) ? "<null>" : this.codingRate));
        sb.append(',');
        sb.append("frequency");
        sb.append('=');
        sb.append(((this.frequency == null) ? "<null>" : this.frequency));
        sb.append(',');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null) ? "<null>" : this.timestamp));
        sb.append(',');
        sb.append("time");
        sb.append('=');
        sb.append(((this.time == null) ? "<null>" : this.time));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
