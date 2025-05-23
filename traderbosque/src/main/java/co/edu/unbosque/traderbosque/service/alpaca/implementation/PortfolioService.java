package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.unbosque.traderbosque.exception.UnauthorizedException;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.HoldingDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderResponseDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.PortfolioDTO;
import co.edu.unbosque.traderbosque.model.DTO.finnhub.QuoteWithSymbolDTO;
import co.edu.unbosque.traderbosque.model.entity.Holding;
import co.edu.unbosque.traderbosque.model.entity.Order;
import co.edu.unbosque.traderbosque.model.entity.User;
import co.edu.unbosque.traderbosque.repository.HoldingRepository;
import co.edu.unbosque.traderbosque.repository.OrderRepository;
import co.edu.unbosque.traderbosque.repository.UserRepository;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IPortfolioService;
import jakarta.servlet.http.HttpSession;

@Service
public class PortfolioService implements IPortfolioService{

    private final HoldingRepository holdingRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MarketService marketService;
    private final AlpacaOrderServiceImpl alpacaService;

    public PortfolioService(HoldingRepository holdingRepository, OrderRepository orderRepository,
                     UserRepository userRepository, MarketService marketService, AlpacaOrderServiceImpl alpacaService){
        this.holdingRepository = holdingRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.marketService = marketService;
        this.alpacaService = alpacaService;
    }
    
    @Override
    public PortfolioDTO getPortfolioForUser(HttpSession session) {
        User user = validarUsuarioSesion(session);

         List<Order> orders = orderRepository.findByUserId(user.getId());

        if (orders == null || orders.isEmpty()) {
            return new PortfolioDTO(new ArrayList<>(), 0.0, 0.0, 0.0, 0.0);
        }

        ObjectMapper mapper = new ObjectMapper();

        for (Order order : orders) {
            try {
                String raw = order.getRawResponse();
                if (raw == null || raw.isEmpty()) continue;

                JsonNode jsonNode = mapper.readTree(raw);
                String alpacaOrderId = jsonNode.get("id").asText();

                OrderResponseDTO alpacaOrder = alpacaService.getOrderById(
                    user.getAlpacaAccountId(),
                    alpacaOrderId
                );

                order.setStatus(alpacaOrder.getStatus());
                order.setFilledQty(alpacaOrder.getFilledQty());
                order.setFilledAvgPrice(alpacaOrder.getFilledAvgPrice());
                order.setFilledAt(alpacaOrder.getFilledAt());
                order.setSubmittedAt(alpacaOrder.getSubmittedAt());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Map<String, List<Order>> ordersBySymbol = orders.stream()
            .filter(order -> {
                try {
                    return "filled".equalsIgnoreCase(order.getStatus()) ||
                        Integer.parseInt(order.getFilledQty()) > 0;
                } catch (Exception e) {
                    return false;
                }
            })
            .collect(Collectors.groupingBy(Order::getSymbol));

        if (ordersBySymbol.isEmpty()) {
            return new PortfolioDTO(new ArrayList<>(), 0.0, 0.0, 0.0, 0.0);
        }

        List<String> symbols = new ArrayList<>(ordersBySymbol.keySet());
        Map<String, Double> currentPrices = getCurrentPricesFromQuotes(symbols);

        List<HoldingDTO> holdings = new ArrayList<>();
        double totalInvested = 0.0;
        double totalCurrentValue = 0.0;

        for (Map.Entry<String, List<Order>> entry : ordersBySymbol.entrySet()) {
            String symbol = entry.getKey();
            List<Order> symbolOrders = entry.getValue();

            int totalQty = 0;
            double totalCost = 0.0;

            for (Order order : symbolOrders) {
                int qty = Integer.parseInt(order.getFilledQty());
                double avgPrice = order.getFilledAvgPrice() != null ? Double.parseDouble(order.getFilledAvgPrice()) : 0.0;
                totalQty += qty;
                totalCost += qty * avgPrice;
            }

            if (totalQty == 0) continue;

            double avgPrice = totalCost / totalQty;
            double currentPrice = currentPrices.getOrDefault(symbol, avgPrice);
            double currentValue = totalQty * currentPrice;
            double gainLossAmount = currentValue - totalCost;
            double gainLossPercentage = totalCost > 0 ? (gainLossAmount / totalCost) * 100 : 0.0;

            Holding holding = holdingRepository.findByUserIdAndSymbol(user, symbol)
                .orElse(new Holding());

            holding.setUserId(user);
            holding.setSymbol(symbol);
            holding.setQty(totalQty);
            holding.setAvgPrice(avgPrice);
            holding.setCurrency("USD");
            holding.setAssetType("stock");
            holding.setLastUpdated(java.time.ZonedDateTime.now());

            holdingRepository.save(holding);

            HoldingDTO holdingDTO = new HoldingDTO(
                symbol,
                totalQty,
                avgPrice,
                currentPrice,
                totalCost,
                currentValue,
                gainLossAmount,
                gainLossPercentage
            );

            holdings.add(holdingDTO);
            totalInvested += totalCost;
            totalCurrentValue += currentValue;
        }

        double totalGainLossAmount = totalCurrentValue - totalInvested;
        double totalGainLossPercentage = totalInvested > 0 ? (totalGainLossAmount / totalInvested) * 100 : 0.0;

        return new PortfolioDTO(
            holdings,
            totalInvested,
            totalCurrentValue,
            totalGainLossAmount,
            totalGainLossPercentage
        );
    }

    private Map<String, Double> getCurrentPricesFromQuotes(List<String> symbols) {
        List<QuoteWithSymbolDTO> quotes = marketService.getRealtimeQuotes(symbols);

        return quotes.stream()
            .collect(Collectors.toMap(
                QuoteWithSymbolDTO::getSymbol,
                quote -> ((Number) quote.getQuote().getCurrentPrice()).doubleValue()
            ));
    }

    private User validarUsuarioSesion(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new UnauthorizedException("Usuario no autenticado");
        }
        return user;
    }


}
