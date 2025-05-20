package co.edu.unbosque.traderbosque.service.alpaca.interfaces;

import co.edu.unbosque.traderbosque.model.DTO.finnhub.QuoteWithSymbolDTO;

import java.util.List;

public interface InterfaceMarketService {
    List<QuoteWithSymbolDTO> getRealtimeQuotes(List<String> symbols);
    List<String> getActiveSymbols();
}

