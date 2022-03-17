
package eu.commean.backend.pojo.mqtt.ttn;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "session_key_id",
        "f_port",
        "f_cnt",
        "frm_payload",
        "decoded_payload",
        "rx_metadata",
        "settings",
        "received_at",
        "consumed_airtime",
        "network_ids"
})
@Generated("jsonschema2pojo")
public class UplinkMessage {

    @JsonProperty("session_key_id")
    private String sessionKeyId;
    @JsonProperty("f_port")
    private Integer fPort;
    @JsonProperty("f_cnt")
    private Integer fCnt;
    @JsonProperty("frm_payload")
    private String frmPayload;
    @JsonProperty("decoded_payload")
    private DecodedPayload decodedPayload;
    @JsonProperty("rx_metadata")
    private List<RxMetadatum> rxMetadata = null;
    @JsonProperty("settings")
    private Settings settings;
    @JsonProperty("received_at")
    private String receivedAt;
    @JsonProperty("consumed_airtime")
    private String consumedAirtime;
    @JsonProperty("network_ids")
    private NetworkIds networkIds;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("session_key_id")
    public String getSessionKeyId() {
        return sessionKeyId;
    }

    @JsonProperty("session_key_id")
    public void setSessionKeyId(String sessionKeyId) {
        this.sessionKeyId = sessionKeyId;
    }

    @JsonProperty("f_port")
    public Integer getfPort() {
        return fPort;
    }

    @JsonProperty("f_port")
    public void setfPort(Integer fPort) {
        this.fPort = fPort;
    }

    @JsonProperty("f_cnt")
    public Integer getfCnt() {
        return fCnt;
    }

    @JsonProperty("f_cnt")
    public void setfCnt(Integer fCnt) {
        this.fCnt = fCnt;
    }

    @JsonProperty("frm_payload")
    public String getFrmPayload() {
        return frmPayload;
    }

    @JsonProperty("frm_payload")
    public void setFrmPayload(String frmPayload) {
        this.frmPayload = frmPayload;
    }

    @JsonProperty("decoded_payload")
    public DecodedPayload getDecodedPayload() {
        return decodedPayload;
    }

    @JsonProperty("decoded_payload")
    public void setDecodedPayload(DecodedPayload decodedPayload) {
        this.decodedPayload = decodedPayload;
    }

    @JsonProperty("rx_metadata")
    public List<RxMetadatum> getRxMetadata() {
        return rxMetadata;
    }

    @JsonProperty("rx_metadata")
    public void setRxMetadata(List<RxMetadatum> rxMetadata) {
        this.rxMetadata = rxMetadata;
    }

    @JsonProperty("settings")
    public Settings getSettings() {
        return settings;
    }

    @JsonProperty("settings")
    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    @JsonProperty("received_at")
    public String getReceivedAt() {
        return receivedAt;
    }

    @JsonProperty("received_at")
    public void setReceivedAt(String receivedAt) {
        this.receivedAt = receivedAt;
    }

    @JsonProperty("consumed_airtime")
    public String getConsumedAirtime() {
        return consumedAirtime;
    }

    @JsonProperty("consumed_airtime")
    public void setConsumedAirtime(String consumedAirtime) {
        this.consumedAirtime = consumedAirtime;
    }

    @JsonProperty("network_ids")
    public NetworkIds getNetworkIds() {
        return networkIds;
    }

    @JsonProperty("network_ids")
    public void setNetworkIds(NetworkIds networkIds) {
        this.networkIds = networkIds;
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
        sb.append(UplinkMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sessionKeyId");
        sb.append('=');
        sb.append(((this.sessionKeyId == null) ? "<null>" : this.sessionKeyId));
        sb.append(',');
        sb.append("fPort");
        sb.append('=');
        sb.append(((this.fPort == null) ? "<null>" : this.fPort));
        sb.append(',');
        sb.append("fCnt");
        sb.append('=');
        sb.append(((this.fCnt == null) ? "<null>" : this.fCnt));
        sb.append(',');
        sb.append("frmPayload");
        sb.append('=');
        sb.append(((this.frmPayload == null) ? "<null>" : this.frmPayload));
        sb.append(',');
        sb.append("decodedPayload");
        sb.append('=');
        sb.append(((this.decodedPayload == null) ? "<null>" : this.decodedPayload));
        sb.append(',');
        sb.append("rxMetadata");
        sb.append('=');
        sb.append(((this.rxMetadata == null) ? "<null>" : this.rxMetadata));
        sb.append(',');
        sb.append("settings");
        sb.append('=');
        sb.append(((this.settings == null) ? "<null>" : this.settings));
        sb.append(',');
        sb.append("receivedAt");
        sb.append('=');
        sb.append(((this.receivedAt == null) ? "<null>" : this.receivedAt));
        sb.append(',');
        sb.append("consumedAirtime");
        sb.append('=');
        sb.append(((this.consumedAirtime == null) ? "<null>" : this.consumedAirtime));
        sb.append(',');
        sb.append("networkIds");
        sb.append('=');
        sb.append(((this.networkIds == null) ? "<null>" : this.networkIds));
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
