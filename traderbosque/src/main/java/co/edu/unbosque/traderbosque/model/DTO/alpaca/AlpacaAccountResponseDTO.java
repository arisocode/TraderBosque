package co.edu.unbosque.traderbosque.model.DTO.alpaca;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AlpacaAccountResponseDTO {

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

    @JsonProperty("contact")
    private ContactDTO contact;

    @JsonProperty("identity")
    private IdentityDTO identity;

    @JsonProperty("disclosures")
    private DisclosuresDTO disclosures;

    @JsonProperty("agreements")
    private List<AgreementDTO> agreements;

    @JsonProperty("trusted_contact")
    private TrustedContactDTO trustedContact;

    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("trading_type")
    private String tradingType;

    @JsonProperty("enabled_assets")
    private List<String> enabledAssets;

}