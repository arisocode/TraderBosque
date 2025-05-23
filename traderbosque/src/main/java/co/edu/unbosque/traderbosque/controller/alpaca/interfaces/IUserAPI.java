package co.edu.unbosque.traderbosque.controller.alpaca.interfaces;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.unbosque.traderbosque.model.DTO.ChangePasswordRequestDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AccountDTO;
import co.edu.unbosque.traderbosque.model.entity.User;
import jakarta.servlet.http.HttpSession;

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

    @GetMapping("/usuarios/byusername/{username}")
    ResponseEntity<?> readUserByName(@PathVariable String username);

    @PostMapping("/checklogin")
    ResponseEntity<?> checkLogin(@RequestBody User user, HttpSession session);

    @GetMapping("/accountId")
    ResponseEntity<Map<String, String>> getAccountId(HttpSession session);
}