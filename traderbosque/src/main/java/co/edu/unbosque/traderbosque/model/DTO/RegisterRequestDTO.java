package co.edu.unbosque.traderbosque.model.DTO;
import jakarta.validation.constraints.*;


public class RegisterRequestDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String username;

    @Size(min = 8)
    private String password;

    // Getters y Setters
}
