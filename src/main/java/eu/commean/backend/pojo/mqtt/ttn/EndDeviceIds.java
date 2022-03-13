
package eu.commean.backend.pojo.mqtt.ttn;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "device_id",
        "application_ids",
        "dev_eui",
        "join_eui",
        "dev_addr"
})
@Generated("jsonschema2pojo")
public class EndDeviceIds {

    @JsonProperty("device_id")
    private String deviceId;
    @JsonProperty("application_ids")
    private ApplicationIds applicationIds;
    @JsonProperty("dev_eui")
    private String devEui;
    @JsonProperty("join_eui")
    private String joinEui;
    @JsonProperty("dev_addr")
    private String devAddr;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("device_id")
    public String getDeviceId() {
        return deviceId;
    }

    @JsonProperty("device_id")
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @JsonProperty("application_ids")
    public ApplicationIds getApplicationIds() {
        return applicationIds;
    }

    @JsonProperty("application_ids")
    public void setApplicationIds(ApplicationIds applicationIds) {
        this.applicationIds = applicationIds;
    }

    @JsonProperty("dev_eui")
    public String getDevEui() {
        return devEui;
    }

    @JsonProperty("dev_eui")
    public void setDevEui(String devEui) {
        this.devEui = devEui;
    }

    @JsonProperty("join_eui")
    public String getJoinEui() {
        return joinEui;
    }

    @JsonProperty("join_eui")
    public void setJoinEui(String joinEui) {
        this.joinEui = joinEui;
    }

    @JsonProperty("dev_addr")
    public String getDevAddr() {
        return devAddr;
    }

    @JsonProperty("dev_addr")
    public void setDevAddr(String devAddr) {
        this.devAddr = devAddr;
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
        sb.append(EndDeviceIds.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("deviceId");
        sb.append('=');
        sb.append(((this.deviceId == null) ? "<null>" : this.deviceId));
        sb.append(',');
        sb.append("applicationIds");
        sb.append('=');
        sb.append(((this.applicationIds == null) ? "<null>" : this.applicationIds));
        sb.append(',');
        sb.append("devEui");
        sb.append('=');
        sb.append(((this.devEui == null) ? "<null>" : this.devEui));
        sb.append(',');
        sb.append("joinEui");
        sb.append('=');
        sb.append(((this.joinEui == null) ? "<null>" : this.joinEui));
        sb.append(',');
        sb.append("devAddr");
        sb.append('=');
        sb.append(((this.devAddr == null) ? "<null>" : this.devAddr));
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
