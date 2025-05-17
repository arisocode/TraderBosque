package co.edu.unbosque.traderbosque.model.DTO.alpaca;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MarketOrderDTO.class, name = "market"),
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("qty")
    private String qty;
    @JsonProperty("side")
    private String side;
    @JsonProperty("type")
    private String type;
    @JsonProperty("time_in_force")
    private String timeInForce;


}