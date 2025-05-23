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
public class HoldingDTO {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("avgPrice")
    private double avgPrice;

    @JsonProperty("currentPrice")
    private double currentPrice;

    @JsonProperty("totalInvested")
    private double totalInvested;

    @JsonProperty("currentValue")
    private double currentValue;

    @JsonProperty("gainLossAmount")
    private double gainLossAmount;

    @JsonProperty("gainLossPercentage")
    private double gainLossPercentage;
}
