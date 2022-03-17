
package eu.commean.backend.pojo.mqtt.ttn;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "gateway_id",
        "eui"
})
@Generated("jsonschema2pojo")
public class GatewayIds {

    @JsonProperty("gateway_id")
    private String gatewayId;
    @JsonProperty("eui")
    private String eui;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("gateway_id")
    public String getGatewayId() {
        return gatewayId;
    }

    @JsonProperty("gateway_id")
    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    @JsonProperty("eui")
    public String getEui() {
        return eui;
    }

    @JsonProperty("eui")
    public void setEui(String eui) {
        this.eui = eui;
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
        sb.append(GatewayIds.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("gatewayId");
        sb.append('=');
        sb.append(((this.gatewayId == null) ? "<null>" : this.gatewayId));
        sb.append(',');
        sb.append("eui");
        sb.append('=');
        sb.append(((this.eui == null) ? "<null>" : this.eui));
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
