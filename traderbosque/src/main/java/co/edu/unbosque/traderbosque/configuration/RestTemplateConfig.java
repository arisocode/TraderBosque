package co.edu.unbosque.traderbosque.configuration;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.AccountDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AlpacaAccountResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }


}