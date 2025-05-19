package co.edu.unbosque.traderbosque.model.DTO.alpaca;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("client_order_id")
    private String clientOrderId;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("submitted_at")
    private String submittedAt;

    @JsonProperty("filled_at")
    private String filledAt;

    @JsonProperty("expired_at")
    private String expiredAt;

    @JsonProperty("canceled_at")
    private String canceledAt;

    @JsonProperty("failed_at")
    private String failedAt;

    @JsonProperty("replaced_at")
    private String replacedAt;

    @JsonProperty("replaced_by")
    private String replacedBy;

    @JsonProperty("replaces")
    private String replaces;

    @JsonProperty("asset_id")
    private String assetId;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("asset_class")
    private String assetClass;

    @JsonProperty("notional")
    private String notional;

    @JsonProperty("qty")
    private String qty;

    @JsonProperty("filled_qty")
    private String filledQty;

    @JsonProperty("filled_avg_price")
    private String filledAvgPrice;

    @JsonProperty("order_class")
    private String orderClass;

    @JsonProperty("order_type")
    private String orderType;

    @JsonProperty("type")
    private String type;

    @JsonProperty("side")
    private String side;

    @JsonProperty("position_intent")
    private String positionIntent;

    @JsonProperty("time_in_force")
    private String timeInForce;

    @JsonProperty("limit_price")
    private String limitPrice;

    @JsonProperty("stop_price")
    private String stopPrice;

    @JsonProperty("status")
    private String status;

    @JsonProperty("extended_hours")
    private String extendedHours;

    @JsonProperty("legs")
    private String legs;

    @JsonProperty("trail_percent")
    private String trailPercent;

    @JsonProperty("trail_price")
    private String trailPrice;

    @JsonProperty("hwm")
    private String hwm;

    @JsonProperty("commission")
    private String commission;

    @JsonProperty("subtag")
    private String subtag;

    @JsonProperty("source")
    private String source;

    @JsonProperty("expires_at")
    private String expiresAt;
}
