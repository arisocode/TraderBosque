package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import co.edu.unbosque.traderbosque.client.AlpacaClient;
import co.edu.unbosque.traderbosque.exception.AlpacaSyncException;
import co.edu.unbosque.traderbosque.exception.EmailAlreadyExistsException;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AlpacaAccountRequestDTO;
import co.edu.unbosque.traderbosque.model.entity.User;
import co.edu.unbosque.traderbosque.repository.UserRepository;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService implements IService<AlpacaAccountRequestDTO, Integer> {

    private final UserRepository repository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final AlpacaClient alpacaClient;
    private final PasswordEncoder passwordEncoder;

    public UserService(
            UserRepository repository,
            ModelMapper modelMapper,
            ObjectMapper objectMapper,
            AlpacaClient alpacaClient,
            PasswordEncoder passwordEncoder
    ) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
        this.alpacaClient = alpacaClient;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void create(AlpacaAccountRequestDTO dto) throws Exception {
        // Validar email
        if (repository.findByEmail(dto.getContact().getEmailAddress()).isPresent()) {
            throw new EmailAlreadyExistsException("El correo ya está registrado.");
        }

        // Crear entidad User (nombre y email desde DTO)
        User user = new User();
        user.setName(dto.getIdentity().getGivenName());
        user.setEmail(dto.getContact().getEmailAddress());
        user.setPhone(dto.getContact().getPhoneNumber());
        user.setUserName(
                (dto.getIdentity().getGivenName() + "." + dto.getIdentity().getFamilyName()).toLowerCase()
        );
        user.setPassword(passwordEncoder.encode("temporal123")); // o recibe contraseña en DTO extendido
        user.setVerified(false);

        // Llamar a Alpaca
        ResponseEntity<String> response = alpacaClient.createAccount(dto);

        if (response.getStatusCode().is2xxSuccessful()) {
            try {
                JsonNode json = objectMapper.readTree(response.getBody());
                user.setAlpacaAccountId(json.get("id").asText());
                user.setAlpacaStatus(json.get("status").asText());
            } catch (Exception e) {
                throw new AlpacaSyncException("Error al procesar la respuesta de Alpaca");
            }
        } else {
            throw new AlpacaSyncException("Error al crear cuenta en Alpaca: " + response.getStatusCode());
        }

        repository.save(user);
        // Aquí podrías enviar email de confirmación si lo deseas
    }

    @Override
    public Optional<AlpacaAccountRequestDTO> read(Integer id) {
        return repository.findById(id)
                .map(user -> modelMapper.map(user, AlpacaAccountRequestDTO.class));
    }

    @Override
    public void update(Integer id, AlpacaAccountRequestDTO dto) {
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));

        user.setName(dto.getIdentity().getGivenName());
        user.setPhone(dto.getContact().getPhoneNumber());

        // Opcional: actualizar username si lo permites
        String nuevoUsername = dto.getIdentity().getGivenName().toLowerCase() + "." +
                dto.getIdentity().getFamilyName().toLowerCase();
        user.setUserName(nuevoUsername);

        repository.save(user);
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<AlpacaAccountRequestDTO> readAll() {
        return repository.findAll().stream()
                .map(user -> modelMapper.map(user, AlpacaAccountRequestDTO.class)) // map real
                .toList();
    }
}


