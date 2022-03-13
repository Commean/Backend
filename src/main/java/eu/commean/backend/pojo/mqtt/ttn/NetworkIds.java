
package eu.commean.backend.pojo.mqtt.ttn;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "net_id",
        "tenant_id",
        "cluster_id"
})
@Generated("jsonschema2pojo")
public class NetworkIds {

    @JsonProperty("net_id")
    private String netId;
    @JsonProperty("tenant_id")
    private String tenantId;
    @JsonProperty("cluster_id")
    private String clusterId;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("net_id")
    public String getNetId() {
        return netId;
    }

    @JsonProperty("net_id")
    public void setNetId(String netId) {
        this.netId = netId;
    }

    @JsonProperty("tenant_id")
    public String getTenantId() {
        return tenantId;
    }

    @JsonProperty("tenant_id")
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    @JsonProperty("cluster_id")
    public String getClusterId() {
        return clusterId;
    }

    @JsonProperty("cluster_id")
    public void setClusterId(String clusterId) {
        this.clusterId = clusterId;
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
        sb.append(NetworkIds.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("netId");
        sb.append('=');
        sb.append(((this.netId == null) ? "<null>" : this.netId));
        sb.append(',');
        sb.append("tenantId");
        sb.append('=');
        sb.append(((this.tenantId == null) ? "<null>" : this.tenantId));
        sb.append(',');
        sb.append("clusterId");
        sb.append('=');
        sb.append(((this.clusterId == null) ? "<null>" : this.clusterId));
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
