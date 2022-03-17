
package eu.commean.backend.pojo.mqtt;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "car",
        "truck",
        "bus",
        "motorbike"
})
@Generated("jsonschema2pojo")
public class Count {

    @JsonProperty("car")
    private Integer car;
    @JsonProperty("truck")
    private Integer truck;
    @JsonProperty("bus")
    private Integer bus;
    @JsonProperty("motorbike")
    private Integer motorbike;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("car")
    public Integer getCar() {
        return car;
    }

    @JsonProperty("car")
    public void setCar(Integer car) {
        this.car = car;
    }

    @JsonProperty("truck")
    public Integer getTruck() {
        return truck;
    }

    @JsonProperty("truck")
    public void setTruck(Integer truck) {
        this.truck = truck;
    }

    @JsonProperty("bus")
    public Integer getBus() {
        return bus;
    }

    @JsonProperty("bus")
    public void setBus(Integer bus) {
        this.bus = bus;
    }

    @JsonProperty("motorbike")
    public Integer getMotorbike() {
        return motorbike;
    }

    @JsonProperty("motorbike")
    public void setMotorbike(Integer motorbike) {
        this.motorbike = motorbike;
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
        sb.append(Count.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("car");
        sb.append('=');
        sb.append(((this.car == null) ? "<null>" : this.car));
        sb.append(',');
        sb.append("truck");
        sb.append('=');
        sb.append(((this.truck == null) ? "<null>" : this.truck));
        sb.append(',');
        sb.append("bus");
        sb.append('=');
        sb.append(((this.bus == null) ? "<null>" : this.bus));
        sb.append(',');
        sb.append("motorbike");
        sb.append('=');
        sb.append(((this.motorbike == null) ? "<null>" : this.motorbike));
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
