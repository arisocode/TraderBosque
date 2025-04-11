package co.edu.unbosque.traderbosque.model.DTO.alpaca;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {


    @JsonProperty("contact")
    private ContactDTO contact;

    @JsonProperty("identity")
    private IdentityDTO identity;

    @JsonProperty("disclosures")
    private DisclosuresDTO disclosures;

    @JsonProperty("agreements")
    private List<AgreementDTO> agreements;

    @JsonProperty("documents")
    private List<DocumentDTO> documents;

    @JsonProperty("trusted_contact")
    private TrustedContactDTO trustedContact;

    @JsonProperty("enabled_assets")
    private List<String> enabledAssets;

    @JsonProperty("account_type")
    private String accountType;

    @JsonProperty("trading_type")
    private String tradingType;

    //Campos personalizados para guardar en la database
    private String username;
    private String password;
}
