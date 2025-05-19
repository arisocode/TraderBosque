package co.edu.unbosque.traderbosque.service.alpaca.interfaces;

import co.edu.unbosque.traderbosque.model.DTO.finnhub.QuoteDTO;

public interface InterfaceFinnhubClient {
    QuoteDTO getQuote(String symbol);
}
