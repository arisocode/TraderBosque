package co.edu.unbosque.traderbosque.service.alpaca.interfaces;

import java.util.List;

public interface InterfaceWatchlistService {
    void saveWatchlist(List<String> symbols);
    List<String> getWatchlist();
}
