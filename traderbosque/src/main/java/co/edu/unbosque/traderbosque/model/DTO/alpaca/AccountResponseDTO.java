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
public class AccountResponseDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("status")
    private String status;

    @JsonProperty("crypto_status")
    private String cryptoStatus;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("last_equity")
    private String lastEquity;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("trading_type")
    private String tradingType;

    @JsonProperty("enabled_assets")
    private List<String> enabledAssets;

}
