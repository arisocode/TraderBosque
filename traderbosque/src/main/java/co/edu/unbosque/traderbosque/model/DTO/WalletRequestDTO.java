package co.edu.unbosque.traderbosque.model.DTO;

import lombok.Getter;
import lombok.Setter;

/*
* @author Mathew Garzon
* Realiza la peticion para guardar la informacion de link  de pago para la billetera
* */
@Getter
@Setter
public class WalletRequestDTO {
    private Long amount;
    private String username;
}
