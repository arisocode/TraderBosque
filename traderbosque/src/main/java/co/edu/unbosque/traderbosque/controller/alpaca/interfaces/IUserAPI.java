package co.edu.unbosque.traderbosque.controller.alpaca.interfaces;

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

    @PutMapping("/{id}")
    ResponseEntity<?> update(@PathVariable Integer id, @RequestBody AccountDTO dto);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id);

    @GetMapping("/listar")
    ResponseEntity<?> readAll();

    /*
    * Para validar credenciales
    * */
    @PostMapping("/checklogin")
    ResponseEntity<?> checkLogin(@RequestBody User user);
}