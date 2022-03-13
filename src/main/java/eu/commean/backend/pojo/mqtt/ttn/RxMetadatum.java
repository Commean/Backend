
package eu.commean.backend.pojo.mqtt.ttn;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "gateway_ids",
        "time",
        "timestamp",
        "rssi",
        "channel_rssi",
        "snr",
        "location",
        "uplink_token"
})
@Generated("jsonschema2pojo")
public class RxMetadatum {

    @JsonProperty("gateway_ids")
    private GatewayIds gatewayIds;
    @JsonProperty("time")
    private String time;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("rssi")
    private Integer rssi;
    @JsonProperty("channel_rssi")
    private Integer channelRssi;
    @JsonProperty("snr")
    private Double snr;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("uplink_token")
    private String uplinkToken;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("gateway_ids")
    public GatewayIds getGatewayIds() {
        return gatewayIds;
    }

    @JsonProperty("gateway_ids")
    public void setGatewayIds(GatewayIds gatewayIds) {
        this.gatewayIds = gatewayIds;
    }

    @JsonProperty("time")
    public String getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(String time) {
        this.time = time;
    }

    @JsonProperty("timestamp")
    public Integer getTimestamp() {
        return timestamp;
    }

    @JsonProperty("timestamp")
    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    @JsonProperty("rssi")
    public Integer getRssi() {
        return rssi;
    }

    @JsonProperty("rssi")
    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    @JsonProperty("channel_rssi")
    public Integer getChannelRssi() {
        return channelRssi;
    }

    @JsonProperty("channel_rssi")
    public void setChannelRssi(Integer channelRssi) {
        this.channelRssi = channelRssi;
    }

    @JsonProperty("snr")
    public Double getSnr() {
        return snr;
    }

    @JsonProperty("snr")
    public void setSnr(Double snr) {
        this.snr = snr;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("uplink_token")
    public String getUplinkToken() {
        return uplinkToken;
    }

    @JsonProperty("uplink_token")
    public void setUplinkToken(String uplinkToken) {
        this.uplinkToken = uplinkToken;
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
        sb.append(RxMetadatum.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("gatewayIds");
        sb.append('=');
        sb.append(((this.gatewayIds == null) ? "<null>" : this.gatewayIds));
        sb.append(',');
        sb.append("time");
        sb.append('=');
        sb.append(((this.time == null) ? "<null>" : this.time));
        sb.append(',');
        sb.append("timestamp");
        sb.append('=');
        sb.append(((this.timestamp == null) ? "<null>" : this.timestamp));
        sb.append(',');
        sb.append("rssi");
        sb.append('=');
        sb.append(((this.rssi == null) ? "<null>" : this.rssi));
        sb.append(',');
        sb.append("channelRssi");
        sb.append('=');
        sb.append(((this.channelRssi == null) ? "<null>" : this.channelRssi));
        sb.append(',');
        sb.append("snr");
        sb.append('=');
        sb.append(((this.snr == null) ? "<null>" : this.snr));
        sb.append(',');
        sb.append("location");
        sb.append('=');
        sb.append(((this.location == null) ? "<null>" : this.location));
        sb.append(',');
        sb.append("uplinkToken");
        sb.append('=');
        sb.append(((this.uplinkToken == null) ? "<null>" : this.uplinkToken));
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
