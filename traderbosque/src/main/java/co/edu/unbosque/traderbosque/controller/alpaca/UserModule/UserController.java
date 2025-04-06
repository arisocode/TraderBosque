package co.edu.unbosque.traderbosque.controller.alpaca.UserModule;

import co.edu.unbosque.traderbosque.controller.alpaca.interfaces.IUserAPI;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AlpacaAccountRequestDTO;
import co.edu.unbosque.traderbosque.service.alpaca.implementation.UserService;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserAPI {

    private final IService<AlpacaAccountRequestDTO, Integer> service;

    @Autowired
    private UserService userService;

    public UserController(IService<AlpacaAccountRequestDTO, Integer> service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> create(AlpacaAccountRequestDTO dto) {
        try {
            service.create(dto);
            return ResponseEntity.status(201).body(dto);
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Error al crear usuario: " + e.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> read(Integer id) {
        return ResponseEntity.status(200).body(service.read(id));
    }

    @Override
    public ResponseEntity<?> update(Integer id, AlpacaAccountRequestDTO dto) {
        service.update(id, dto);
        return ResponseEntity.status(200).body(dto);
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        service.delete(id);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<?> readAll() {
        return ResponseEntity.status(200).body(service.readAll());
    }
}