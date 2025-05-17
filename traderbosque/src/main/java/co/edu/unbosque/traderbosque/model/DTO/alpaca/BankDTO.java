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
public class BankDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("bank_code")
    private String bankCode;

    @JsonProperty("bank_code_type")
    private String bankCodeType;

    @JsonProperty("account_number")
    private String accountNumber;

    @JsonProperty("country")
    private String country;

    @JsonProperty("state_province")
    private String stateProvince;

    @JsonProperty("postal_code")
    private String postalCode;

    @JsonProperty("city")
    private String city;

    @JsonProperty("street_address")
    private String streetAddress;

}
