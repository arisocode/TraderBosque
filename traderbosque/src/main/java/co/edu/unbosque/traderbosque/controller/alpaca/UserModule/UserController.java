package co.edu.unbosque.traderbosque.controller.alpaca.UserModule;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.traderbosque.controller.alpaca.interfaces.IUserAPI;
import co.edu.unbosque.traderbosque.model.DTO.ChangePasswordRequestDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AccountDTO;
import co.edu.unbosque.traderbosque.model.entity.User;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IService;
import jakarta.servlet.http.HttpSession;

<<<<<<< HEAD
=======
import java.util.Optional;

>>>>>>> b4b7efa (Pruebas unitarias)
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
        Optional<AccountDTO> optDto = service.read(id);
        if(optDto.isPresent()) {
            return ResponseEntity.status(200).body(service.read(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public ResponseEntity<?> update(Integer id, AccountDTO dto) {
        service.update(id, dto);
        return ResponseEntity.status(200).body(dto);
    }

    @Override
    public ResponseEntity<?> updatePasswordOnly(ChangePasswordRequestDTO user) {

        int status = service.updatePasswordOnly(user);
        if (status == 0) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
        } else {
            return new ResponseEntity<>("Password incorrect", HttpStatus.UNAUTHORIZED);
        }
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

    @Override
    public ResponseEntity<?> readAllUsers(int id) {
        return ResponseEntity.status(200).body(service.readAllUsers(id));
    }

    /*
    * Leer con el nombre de usuario
    * */
    @Override
    public ResponseEntity<?> readUserByName(String username) {
        return ResponseEntity.status(200).body(service.readUsername(username));
    }

    /*
    Validar credenciales
    * */
    @Override
    public ResponseEntity<?> checkLogin(User user, HttpSession session){
        int status = service.validateCredentials(user.getUserName(), user.getPassword());

        if (status == 0) {
            User finisheUser = service.readUsername(user.getUserName());
            session.setAttribute("user", finisheUser);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(user);
        } else {
            return new ResponseEntity<>("Username or password incorrect", HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public ResponseEntity<Map<String, String>> getAccountId(HttpSession session) {
        User user = service.validarUsuarioSesion(session);

        Map<String, String> response = new HashMap<>();
        response.put("accountId", user.getAlpacaAccountId());

        return ResponseEntity.ok(response);
    }

}
