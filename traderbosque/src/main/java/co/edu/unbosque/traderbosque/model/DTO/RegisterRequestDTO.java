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

    private String lastName;
    private String dateOfBirth;
    private String taxId;
    private String ipAddress;

    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;

}
