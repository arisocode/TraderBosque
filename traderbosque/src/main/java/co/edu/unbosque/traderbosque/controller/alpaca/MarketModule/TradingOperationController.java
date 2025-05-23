package co.edu.unbosque.traderbosque.controller.alpaca.MarketModule;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderResponseDTO;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.ITradingOperationService;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/v1/api/trading")
public class TradingOperationController  {

    private final ITradingOperationService service;

    public TradingOperationController(ITradingOperationService service) {
        this.service = service;
    }

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO dto, HttpSession session) {
        OrderResponseDTO response = service.createOrder(dto, session);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/account/{alpacaAccountId}")
    public ResponseEntity<Object> getAccountInfo(@PathVariable String alpacaAccountId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("CKSB24KB2FW08NFZKOM6", "jiW0ruUk2V1XSplH3y8uN3TNQ09voefbu784NMr4"); // Reemplaza por tus claves reales
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                "https://broker-api.sandbox.alpaca.markets/v1/accounts/" + alpacaAccountId,
                HttpMethod.GET,
                request,
                Object.class
        );

        return ResponseEntity.ok(response.getBody());
    }
}



