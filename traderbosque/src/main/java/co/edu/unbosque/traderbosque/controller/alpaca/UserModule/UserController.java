package co.edu.unbosque.traderbosque.controller.alpaca.UserModule;

import co.edu.unbosque.traderbosque.controller.alpaca.interfaces.IUserAPI;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AccountDTO;

import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements IUserAPI {

    private final IService<AccountDTO, Integer> service;

    public UserController(IService<AccountDTO, Integer> service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<?> create(AccountDTO dto) throws Exception {

            service.create(dto);
            return ResponseEntity.status(201).body(dto);

    }

    @Override
    public ResponseEntity<?> read(Integer id) {
        return ResponseEntity.status(200).body(service.read(id));
    }

    @Override
    public ResponseEntity<?> update(Integer id, AccountDTO dto) {
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
