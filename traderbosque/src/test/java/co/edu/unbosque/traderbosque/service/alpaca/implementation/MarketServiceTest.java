package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import co.edu.unbosque.traderbosque.config.AlpacaProperties;
import co.edu.unbosque.traderbosque.model.Asset;
import co.edu.unbosque.traderbosque.model.DTO.finnhub.QuoteDTO;
import co.edu.unbosque.traderbosque.model.DTO.finnhub.QuoteWithSymbolDTO;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.InterfaceFinnhubClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockRestServiceServer
class MarketServiceTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AlpacaProperties alpacaProperties;

    private InterfaceFinnhubClient finnhubClient;

    private MarketService marketService;

    @Autowired
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        finnhubClient = mock(InterfaceFinnhubClient.class);
        alpacaProperties = mock(AlpacaProperties.class);
        restTemplate = mock(RestTemplate.class);

        when(alpacaProperties.getKey()).thenReturn("dummyKey");
        when(alpacaProperties.getSecret()).thenReturn("dummySecret");

        marketService = new MarketService(finnhubClient, alpacaProperties, restTemplate);
    }

    @Test
    void testGetRealtimeQuotes() {
        when(finnhubClient.getQuote("AAPL")).thenReturn(new QuoteDTO());
        when(finnhubClient.getQuote("GOOG")).thenReturn(new QuoteDTO());

        List<QuoteWithSymbolDTO> quotes = marketService.getRealtimeQuotes(List.of("AAPL", "GOOG"));

        assertEquals(2, quotes.size());
        assertEquals("AAPL", quotes.get(0).getSymbol());
        assertEquals("GOOG", quotes.get(1).getSymbol());
    }

    @Test
    void testGetActiveSymbols() {
        Asset activeAsset = new Asset();
        activeAsset.setStatus("active");
        activeAsset.setSymbol("AAPL");

        Asset inactiveAsset = new Asset();
        inactiveAsset.setStatus("inactive");
        inactiveAsset.setSymbol("MSFT");

        Asset[] assets = new Asset[]{activeAsset, inactiveAsset};

        ResponseEntity<Asset[]> responseEntity = new ResponseEntity<>(assets, HttpStatus.OK);

        when(restTemplate.exchange(
                eq("https://broker-api.sandbox.alpaca.markets/v1/assets"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(Asset[].class)
        )).thenReturn(responseEntity);

        List<String> activeSymbols = marketService.getActiveSymbols();

        assertEquals(1, activeSymbols.size());
        assertEquals("AAPL", activeSymbols.get(0));
    }
}
