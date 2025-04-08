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
public class IdentityDTO {

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("date_of_birth")
    private String dateOfBirth;

    @JsonProperty("tax_id")
    private String taxId;

    @JsonProperty("tax_id_type")
    private String taxIdType;

    @JsonProperty("country_of_citizenship")
    private String citizenship;

    @JsonProperty("country_of_birth")
    private String birthCountry;

    @JsonProperty("country_of_tax_residence")
    private String taxResidence;

    @JsonProperty("funding_source")
    private List<String> fundingSource;

    @JsonProperty("party_type")
    private String partyType;
}
