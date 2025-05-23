package co.edu.unbosque.traderbosque.model.DTO.alpaca;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDTO {

    @JsonProperty("holdings")
    private List<HoldingDTO> holdings;

    @JsonProperty("totalInvested")
    private double totalInvested;

    @JsonProperty("totalCurrentValue")
    private double totalCurrentValue;

    @JsonProperty("totalGainLossAmount")
    private double totalGainLossAmount;

    @JsonProperty("totalGainLossPercentage")
    private double totalGainLossPercentage;
    
}
