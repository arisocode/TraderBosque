package co.edu.unbosque.traderbosque.client;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.AccountDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AlpacaAccountResponseDTO;
import jakarta.annotation.PostConstruct;
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

    public ResponseEntity<AlpacaAccountResponseDTO> createAccount(AccountDTO request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBasicAuth(apiKey, secretKey);

        HttpEntity<AccountDTO> entity = new HttpEntity<>(request, headers);

        return restTemplate.exchange(
                baseUrl + "/v1/accounts",
                HttpMethod.POST,
                entity,
                AlpacaAccountResponseDTO.class
        );
    }



    @PostConstruct
    public void printCredentials() {
        System.out.println("✅ AlpacaClient cargado con:");
        System.out.println("🔑 API KEY: " + apiKey);
        System.out.println("🔒 API SECRET: " + secretKey);
        System.out.println("🌐 BASE URL: " + baseUrl);
    }
}