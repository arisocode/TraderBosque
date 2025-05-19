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
public class TransferDTO {

    @JsonProperty("transfer_type")
    private String transferType;

    @JsonProperty("relationship_id")
    private String relationshipId;

    @JsonProperty("amount")
    private String amount;

    @JsonProperty("direction")
    private String direction;

}