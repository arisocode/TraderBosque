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
public class OrderDTO {

    @JsonProperty("symbol")
    private String symbol;
    @JsonProperty("qty")
    private int qty;
    @JsonProperty("side")
    private String side;
    @JsonProperty("type")
    private String type;
    @JsonProperty("time_in_force")
    private String timeInForce;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double currentPrice;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Double limitPrice;


}