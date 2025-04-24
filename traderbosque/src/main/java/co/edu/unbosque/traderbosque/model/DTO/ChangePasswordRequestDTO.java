package co.edu.unbosque.traderbosque.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/*
* Esta clase es para manejar la petición del cambio de contraseña
* @author Mathew Garzón
* @version 1.0
* */
@Getter
@Setter
public class ChangePasswordRequestDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String oldPassword;
    @NotBlank
    private String newPassword;

    @Override
    public String toString() {
        return "ChangePasswordRequestDTO{" +
                "username='" + username + '\'' +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
