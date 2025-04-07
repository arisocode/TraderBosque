package co.edu.unbosque.traderbosque.client;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.AlpacaAccountRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AlpacaClient {

    @Value("${alpaca.api.base-url}")
    private String baseUrl;

    @Value("${alpaca.api.key}")
    private String apiKey;

    @Value("${alpaca.api.secret}")
    private String secretKey;

    private final RestTemplate restTemplate;

    public AlpacaClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> createAccount(AlpacaAccountRequestDTO request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("APCA-API-KEY-ID", apiKey);
        headers.set("APCA-API-SECRET-KEY", secretKey);

        HttpEntity<AlpacaAccountRequestDTO> entity = new HttpEntity<>(request, headers);

        return restTemplate.postForEntity(
                baseUrl + "/v1/accounts",
                entity,
                String.class
        );
    }
}