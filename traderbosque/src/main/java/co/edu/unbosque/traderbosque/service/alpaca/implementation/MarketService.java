package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import co.edu.unbosque.traderbosque.model.DTO.finnhub.QuoteWithSymbolDTO;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.InterfaceFinnhubClient;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.InterfaceMarketService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketService implements InterfaceMarketService {

    private final InterfaceFinnhubClient finnhubClient;

    public MarketService(InterfaceFinnhubClient finnhubClient) {
        this.finnhubClient = finnhubClient;
    }

    @Override
    public List<QuoteWithSymbolDTO> getRealtimeQuotes(List<String> symbols) {
        return symbols.stream()
                .map(symbol -> new QuoteWithSymbolDTO(symbol, finnhubClient.getQuote(symbol)))
                .collect(Collectors.toList());
    }
}

