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
public class AgreementDTO {

    @JsonProperty("agreement")
    private String agreement;

    @JsonProperty("signed_at")
    private String signedAt; // formato ISO 8601 (Z)

    @JsonProperty("ip_address")
    private String ipAddress;

    @JsonProperty("revision")
    private String revision;
}
