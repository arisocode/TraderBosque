package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.edu.unbosque.traderbosque.config.AlpacaProperties;
import co.edu.unbosque.traderbosque.model.Asset;
import co.edu.unbosque.traderbosque.model.DTO.finnhub.QuoteWithSymbolDTO;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.InterfaceFinnhubClient;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.InterfaceMarketService;

@Service
public class MarketService implements InterfaceMarketService {

    private final InterfaceFinnhubClient finnhubClient;
    @Autowired
    private AlpacaProperties alpacaProperties;
    @Autowired
    private RestTemplate restTemplate;

    public MarketService(InterfaceFinnhubClient finnhubClient, AlpacaProperties alpacaProperties, RestTemplate restTemplate) {
        this.finnhubClient = finnhubClient;
        this.alpacaProperties = alpacaProperties;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<QuoteWithSymbolDTO> getRealtimeQuotes(List<String> symbols) {
        return symbols.stream()
                .map(symbol -> new QuoteWithSymbolDTO(symbol, finnhubClient.getQuote(symbol)))
                .collect(Collectors.toList());
    }

    /*
    * Consigue las acciones
    * */
    @Override
    public List<String> getActiveSymbols() {
        String credentials = alpacaProperties.getKey() + ":" + alpacaProperties.getSecret();
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<Asset[]> response = restTemplate.exchange(
                "https://broker-api.sandbox.alpaca.markets/v1/assets",
                HttpMethod.GET,
                entity,
                Asset[].class
        );

        Asset[] assets = response.getBody();

        return Arrays.stream(assets)
                .filter(asset -> "active".equals(asset.getStatus()))
                .map(Asset::getSymbol)
                .collect(Collectors.toList());
    }
}

