package co.edu.unbosque.traderbosque.controller.alpaca.interfaces;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ITradingOperationAPI {

    @GetMapping("/account/{alpacaAccountId}")
    ResponseEntity<?> getAccountInfo(@PathVariable String alpacaAccountId);

    @PostMapping("order")
    ResponseEntity<?> createOrder(@RequestBody OrderDTO dto);

}
