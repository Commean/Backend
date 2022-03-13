
package eu.commean.backend.pojo.mqtt;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "atip",
        "count",
        "id",
        "time"
})
@Generated("jsonschema2pojo")
public class Payload {

    @JsonProperty("atip")
    private Integer atip;
    @JsonProperty("count")
    private Count count;
    @JsonProperty("id")
    private String id;
    @JsonProperty("time")
    private Double time;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("atip")
    public Integer getAtip() {
        return atip;
    }

    @JsonProperty("atip")
    public void setAtip(Integer atip) {
        this.atip = atip;
    }

    @JsonProperty("count")
    public Count getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Count count) {
        this.count = count;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("time")
    public Double getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Double time) {
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
        sb.append(Payload.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("atip");
        sb.append('=');
        sb.append(((this.atip == null) ? "<null>" : this.atip));
        sb.append(',');
        sb.append("count");
        sb.append('=');
        sb.append(((this.count == null) ? "<null>" : this.count));
        sb.append(',');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
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
