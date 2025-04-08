package co.edu.unbosque.traderbosque.model.DTO.alpaca;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisclosuresDTO {

    @JsonProperty("is_control_person")
    private boolean isControlPerson;

    @JsonProperty("is_affiliated_exchange_or_finra")
    private boolean isAffiliatedExchangeOrFinra;

    @JsonProperty("is_politically_exposed")
    private boolean isPoliticallyExposed;

    @JsonProperty("immediate_family_exposed")
    private boolean immediateFamilyExposed;

    @JsonProperty("is_affiliated_exchange_or_iiroc")
    private boolean isAffiliatedExchangeOrIiroc;

    @JsonProperty("is_discretionary")
    private Boolean isDiscretionary; // mejor con wrapper para permitir null
}
