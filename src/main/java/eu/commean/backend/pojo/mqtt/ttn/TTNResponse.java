
package eu.commean.backend.pojo.mqtt.ttn;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "end_device_ids",
        "correlation_ids",
        "received_at",
        "uplink_message"
})
@Generated("jsonschema2pojo")
public class TTNResponse {

    @JsonProperty("end_device_ids")
    private EndDeviceIds endDeviceIds;
    @JsonProperty("correlation_ids")
    private List<String> correlationIds = null;
    @JsonProperty("received_at")
    private String receivedAt;
    @JsonProperty("uplink_message")
    private UplinkMessage uplinkMessage;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("end_device_ids")
    public EndDeviceIds getEndDeviceIds() {
        return endDeviceIds;
    }

    @JsonProperty("end_device_ids")
    public void setEndDeviceIds(EndDeviceIds endDeviceIds) {
        this.endDeviceIds = endDeviceIds;
    }

    @JsonProperty("correlation_ids")
    public List<String> getCorrelationIds() {
        return correlationIds;
    }

    @JsonProperty("correlation_ids")
    public void setCorrelationIds(List<String> correlationIds) {
        this.correlationIds = correlationIds;
    }

    @JsonProperty("received_at")
    public String getReceivedAt() {
        return receivedAt;
    }

    @JsonProperty("received_at")
    public void setReceivedAt(String receivedAt) {
        this.receivedAt = receivedAt;
    }

    @JsonProperty("uplink_message")
    public UplinkMessage getUplinkMessage() {
        return uplinkMessage;
    }

    @JsonProperty("uplink_message")
    public void setUplinkMessage(UplinkMessage uplinkMessage) {
        this.uplinkMessage = uplinkMessage;
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
        sb.append(TTNResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("endDeviceIds");
        sb.append('=');
        sb.append(((this.endDeviceIds == null) ? "<null>" : this.endDeviceIds));
        sb.append(',');
        sb.append("correlationIds");
        sb.append('=');
        sb.append(((this.correlationIds == null) ? "<null>" : this.correlationIds));
        sb.append(',');
        sb.append("receivedAt");
        sb.append('=');
        sb.append(((this.receivedAt == null) ? "<null>" : this.receivedAt));
        sb.append(',');
        sb.append("uplinkMessage");
        sb.append('=');
        sb.append(((this.uplinkMessage == null) ? "<null>" : this.uplinkMessage));
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
