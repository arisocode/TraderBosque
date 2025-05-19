package co.edu.unbosque.traderbosque.model.DTO.alpaca;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class MarketOrderDTO extends OrderDTO {

    private String market;
    public MarketOrderDTO() {}
    public MarketOrderDTO(String market) {}




}