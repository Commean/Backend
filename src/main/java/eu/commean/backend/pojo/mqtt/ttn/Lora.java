
package eu.commean.backend.pojo.mqtt.ttn;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "bandwidth",
        "spreading_factor"
})
@Generated("jsonschema2pojo")
public class Lora {

    @JsonProperty("bandwidth")
    private Integer bandwidth;
    @JsonProperty("spreading_factor")
    private Integer spreadingFactor;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bandwidth")
    public Integer getBandwidth() {
        return bandwidth;
    }

    @JsonProperty("bandwidth")
    public void setBandwidth(Integer bandwidth) {
        this.bandwidth = bandwidth;
    }

    @JsonProperty("spreading_factor")
    public Integer getSpreadingFactor() {
        return spreadingFactor;
    }

    @JsonProperty("spreading_factor")
    public void setSpreadingFactor(Integer spreadingFactor) {
        this.spreadingFactor = spreadingFactor;
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
        sb.append(Lora.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("bandwidth");
        sb.append('=');
        sb.append(((this.bandwidth == null) ? "<null>" : this.bandwidth));
        sb.append(',');
        sb.append("spreadingFactor");
        sb.append('=');
        sb.append(((this.spreadingFactor == null) ? "<null>" : this.spreadingFactor));
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
