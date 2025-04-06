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
public class AlpacaAccountRequestDTO {


    @JsonProperty("contact")
    private ContactDTO contact;

    @JsonProperty("identity")
    private IdentityDTO identity;

    @JsonProperty("disclosures")
    private DisclosuresDTO disclosures;

    @JsonProperty("agreements")
    private List<AgreementDTO> agreements;


}
