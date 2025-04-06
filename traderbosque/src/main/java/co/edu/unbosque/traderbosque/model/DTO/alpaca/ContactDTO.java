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
public class ContactDTO {

    @JsonProperty ("email_address")
    private String emailAddress;

    @JsonProperty ("phone_number")
    private String phoneNumber;

    @JsonProperty ("street_address")
    private List<String> streetAddress;

    @JsonProperty("city")
    private String city;

    @JsonProperty ("state")
    private String state;

    @JsonProperty ("postal_code")
    private String postalCode;

    @JsonProperty ("country")
    private String country;

}
