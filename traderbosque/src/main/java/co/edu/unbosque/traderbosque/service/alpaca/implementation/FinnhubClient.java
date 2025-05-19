package co.edu.unbosque.traderbosque.service.alpaca.implementation;
import co.edu.unbosque.traderbosque.model.DTO.finnhub.QuoteDTO;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.InterfaceFinnhubClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FinnhubClient implements InterfaceFinnhubClient {

    @Value("https://finnhub.io/api/v1")
    private String apiUrl;

    @Value("d0kefqhr01qn937kbpm0d0kefqhr01qn937kbpmg")
    private String apiKey;

    private final RestTemplate restTemplate;

    public FinnhubClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    @Override
    public QuoteDTO getQuote(String symbol) {
        String url = String.format("%s/quote?symbol=%s&token=%s", apiUrl, symbol, apiKey);
        return restTemplate.getForObject(url, QuoteDTO.class);
    }

}