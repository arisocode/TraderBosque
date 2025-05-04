package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import co.edu.unbosque.traderbosque.client.AlpacaClient;
import co.edu.unbosque.traderbosque.exception.AlpacaSyncException;
import co.edu.unbosque.traderbosque.exception.EmailAlreadyExistsException;
import co.edu.unbosque.traderbosque.model.DTO.ChangePasswordRequestDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AccountDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AlpacaAccountResponseDTO;
import co.edu.unbosque.traderbosque.model.entity.User;
import co.edu.unbosque.traderbosque.repository.UserRepository;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IService<AccountDTO, Integer> {

    private final RestTemplate restTemplate;
    private final UserRepository repository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;


    private String apiKey = "CKYFYBH67LUIN6NP0VHU";
    private String apiSecret = "IjVKirJjCO4VYBs7DLPVywq8A7awybu5hOuMyUyr";

    private final String baseUrl = "https://broker-api.sandbox.alpaca.markets/v1/accounts";

    public UserService(RestTemplate restTemplate, UserRepository repository, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }


    @Override
    @Transactional
    public AlpacaAccountResponseDTO create(AccountDTO dto) throws Exception {

        if (repository.findByEmail(dto.getContact().getEmailAddress()).isPresent()) {
            throw new EmailAlreadyExistsException("El correo ya está registrado.");
        }

        User user = new User();
        user.setName(dto.getIdentity().getGivenName());
        user.setEmail(dto.getContact().getEmailAddress());
        user.setPhone(dto.getContact().getPhoneNumber());
        user.setUserName(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setVerified(false);

        AccountDTO dtoAlpaca = new AccountDTO();
        dtoAlpaca.setContact(dto.getContact());
        dtoAlpaca.setIdentity(dto.getIdentity());
        dtoAlpaca.setDisclosures(dto.getDisclosures());
        dtoAlpaca.setAgreements(dto.getAgreements());
        dtoAlpaca.setDocuments(dto.getDocuments());
        dtoAlpaca.setTrustedContact(dto.getTrustedContact());
        dtoAlpaca.setEnabledAssets(dto.getEnabledAssets());
        dtoAlpaca.setAccountType(dto.getAccountType());
        dtoAlpaca.setTradingType(dto.getTradingType());

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(apiKey, apiSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        System.out.println("📤 Enviando JSON a Alpaca:");
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(dtoAlpaca));

        HttpEntity<AccountDTO> request = new HttpEntity<>(dtoAlpaca, headers);

        try {
            ResponseEntity<AlpacaAccountResponseDTO> response = restTemplate.exchange(
                    baseUrl ,
                    HttpMethod.POST,
                    request,
                    AlpacaAccountResponseDTO.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                AlpacaAccountResponseDTO alpaca = response.getBody();
                user.setAlpacaAccountId(alpaca.getId());
                user.setAlpacaStatus(alpaca.getStatus());
                repository.save(user);
                return alpaca;
            } else {
                throw new AlpacaSyncException("Error al crear cuenta en Alpaca: " + response.getStatusCode());
            }

        } catch (Exception ex) {
            throw new AlpacaSyncException("Error al conectar con Alpaca: " + ex.getMessage());
        }
    }



    @Override
    public Optional<AccountDTO> read(Integer id) {
        return Optional.empty();
    }

    @Override
    public void update(Integer id, AccountDTO dto) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<AccountDTO> readAll() {
        return List.of();
    }


    @Override
    public User readAllUsers(int id) {
        List<User> list  = repository.findAll();

        for (User user : list) {
            if(user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public List<User> readAllUser() {
        return repository.findAll();
    }

    /*
    * Para la lectura a partir del nombre de usuario
    * */
    public User readUsername(String username) {
        for (User u : repository.findAll()) {
            if(u.getUserName().equals(username)) {
                return u;
            }
        }

        return null;
    }

    /*
    * Realiza la validacion de credenciales accediendo al repositorio del ususario.
    * */
    @Override
    public int validateCredentials(String username, String password) {
        for (User u : readAllUser()) {
            if (u.getUserName().equals(username)) {

                if (u.getPassword().equals(password)){
                    return 0;
                }
            }
        }
        return 1;
    }

    /*
    * Para actualizar unicamente la contraseña usando como parametro un DTO de petición para cambiar la contraseña.
    * */
    public int updatePasswordOnly(ChangePasswordRequestDTO user) {
        System.out.println(user.toString());
        for (User u : readAllUser()) {
            if (u.getUserName().equals(user.getUsername())) {
                if (u.getPassword().equals(user.getOldPassword())){
                    u.setPassword(user.getNewPassword());
                    repository.save(u);
                    return 0;
                }
            }
        }
        return 1;
    }
}





