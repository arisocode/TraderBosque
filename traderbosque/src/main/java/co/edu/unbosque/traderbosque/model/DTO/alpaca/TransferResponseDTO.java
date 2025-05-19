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
public class TransferResponseDTO {


    @JsonProperty("id")
    private String id;

    @JsonProperty("relationship_id")
    private String relationshipId;

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("status")
    private String status;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("instant_amount")
    private String instantAmount;

    @JsonProperty("direction")
    private String direction;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("expires_at")
    private String expiresAt;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("hold_until")
    private String holdUntil;

    @JsonProperty("requested_amount")
    private String requestedAmount;

    @JsonProperty("fee")
    private String fee;

    @JsonProperty("fee_payment_method")
    private String feePaymentMethod;

}