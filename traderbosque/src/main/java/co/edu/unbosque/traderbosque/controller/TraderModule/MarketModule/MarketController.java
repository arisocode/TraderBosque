package co.edu.unbosque.traderbosque.controller.TraderModule.MarketModule;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    private final ApiAlpacaClient alpacaClient;

    public MarketController(ApiAlpacaClient alpacaClient) {
        this.alpacaClient = alpacaClient;
    }

    @GetMapping("/data")
    public ResponseEntity<?> getMarketData(@RequestParam String symbol) {

        String url = "https://data.alpaca.markets/v2/stocks/bars?symbols=" + symbol + "&timeframe=1Min";

        System.out.println("Consultando API de Alpaca: " + url);
        
        HttpEntity<String> entity = new HttpEntity<>(alpacaClient.getAuthHeaders());
        RestTemplate restTemplate = alpacaClient.getRestTemplate();
        
        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            System.out.println("Respuesta de Alpaca: " + response.getBody());
            
            if (response.getBody() != null && response.getBody().containsKey("bars")) {
                return ResponseEntity.ok(response.getBody());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron datos del mercado.");
            }
        } catch (Exception e) {
            System.err.println("Error al consultar Alpaca: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener datos del mercado.");
        }
    }
}
