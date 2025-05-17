package co.edu.unbosque.traderbosque.controller.alpaca.MarketModule;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderResponseDTO;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.ITradingOperationService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/v1/api/trading")
public class TradingOperationController  {

    private final ITradingOperationService service;

    public TradingOperationController(ITradingOperationService service) {
        this.service = service;
    }

    @PostMapping("/order")
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderDTO dto) {
        OrderResponseDTO response = service.createOrder(dto); // NO se envía el ID
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



