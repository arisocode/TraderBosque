package co.edu.unbosque.traderbosque.controller.alpaca.interfaces;

import co.edu.unbosque.traderbosque.model.DTO.ChangePasswordRequestDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AccountDTO;
import co.edu.unbosque.traderbosque.model.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/usuario/v1")
public interface IUserAPI {

    @PostMapping("/crear")
    ResponseEntity<?> create(@RequestBody AccountDTO dto) throws Exception;

    @GetMapping("/{id}")
    ResponseEntity<?> read(@PathVariable Integer id);

    @PutMapping("/update/{id}")
    ResponseEntity<?> update(@PathVariable Integer id, @RequestBody AccountDTO dto);

    @PutMapping("/updatePassword")
    ResponseEntity<?> updatePasswordOnly(@RequestBody ChangePasswordRequestDTO user);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id);

    @GetMapping("/listar")
    ResponseEntity<?> readAll();

    @GetMapping("/usuarios/{id}")
    ResponseEntity<?> readAllUsers(@PathVariable int id);

    /*
    * Para validar conforme al nombre de usuario
    * */
    @GetMapping("/usuarios/byusername/{username}")
    ResponseEntity<?> readUserByName(@PathVariable String username);

    /*
    * Para validar credenciales
    * */
    @PostMapping("/checklogin")
    ResponseEntity<?> checkLogin(@RequestBody User user);
}