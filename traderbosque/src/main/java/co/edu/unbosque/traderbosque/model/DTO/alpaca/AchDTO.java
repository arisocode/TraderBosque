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
public class AchDTO {

    @JsonProperty("account_owner_name")
    private String accountOwnerName;

    @JsonProperty("bank_account_type")
    private String bankAccountType;

    @JsonProperty("bank_account_number")
    private String bankAccountNumber;

    @JsonProperty("bank_routing_number")
    private String bankRoutingNumber;

    @JsonProperty("nickname")
    private String nickname;

}
