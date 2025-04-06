package co.edu.unbosque.traderbosque.controller.alpaca.interfaces;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.AlpacaAccountRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/usuario/v1")
public interface IUserAPI {

    @PostMapping("/crear")
    ResponseEntity<?> create(@RequestBody AlpacaAccountRequestDTO dto);

    @GetMapping("/buscar/{id}")
    ResponseEntity<?> read(@PathVariable Integer id);

    @PutMapping("/actualizar/{id}")
    ResponseEntity<?> update(@PathVariable Integer id, @RequestBody AlpacaAccountRequestDTO dto);

    @DeleteMapping("/eliminar/{id}")
    ResponseEntity<?> delete(@PathVariable Integer id);

    @GetMapping("/listar")
    ResponseEntity<?> readAll();
}