package co.edu.unbosque.traderbosque.controller.finnhub;

import co.edu.unbosque.traderbosque.model.DTO.finnhub.QuoteWithSymbolDTO;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.InterfaceMarketService;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.InterfaceWatchlistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/market")
@CrossOrigin(origins = "http://localhost:3000")
public class MarketController {

    private final InterfaceMarketService marketService;
    private final InterfaceWatchlistService watchlistService;

    public MarketController(InterfaceMarketService marketService, InterfaceWatchlistService watchlistService) {
        this.marketService = marketService;
        this.watchlistService = watchlistService;
    }

    @GetMapping("/quotes")
    public ResponseEntity<List<QuoteWithSymbolDTO>> getQuotes(@RequestParam(required = false) List<String> symbols) {
        if (symbols == null || symbols.isEmpty()) {
            symbols = marketService.getActiveSymbols().stream()
                    .limit(10)
                    .collect(Collectors.toList());
        }
        List<QuoteWithSymbolDTO> quotes = marketService.getRealtimeQuotes(symbols);
        return ResponseEntity.ok(quotes);
    }

    @PostMapping("/watchlist")
    public ResponseEntity<String> saveWatchlist(@RequestBody List<String> symbols) {
        watchlistService.saveWatchlist(symbols);
        return ResponseEntity.ok("Watchlist guardada correctamente");
    }

    @GetMapping("/watchlist")
    public ResponseEntity<List<QuoteWithSymbolDTO>> getWatchlist() {
        List<String> symbols = watchlistService.getWatchlist();
        return ResponseEntity.ok(marketService.getRealtimeQuotes(symbols));
    }
}