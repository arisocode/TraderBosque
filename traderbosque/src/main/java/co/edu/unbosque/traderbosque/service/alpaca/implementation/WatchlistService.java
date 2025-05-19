package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import co.edu.unbosque.traderbosque.service.alpaca.interfaces.InterfaceWatchlistService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WatchlistService implements InterfaceWatchlistService {

    private final Map<String, List<String>> watchlists = new HashMap<>();
    private static final String DEFAULT_USER = "usuario-demo";

    @Override
    public void saveWatchlist(List<String> symbols) {
        if (symbols == null || symbols.isEmpty()) return;

        List<String> listaLimpia = symbols.stream()
                .filter(s -> s != null && !s.trim().isEmpty())
                .map(String::toUpperCase)
                .distinct()
                .toList();

        watchlists.put(DEFAULT_USER, listaLimpia);
    }

    @Override
    public List<String> getWatchlist() {
        return watchlists.getOrDefault(DEFAULT_USER, new ArrayList<>());
    }
}
