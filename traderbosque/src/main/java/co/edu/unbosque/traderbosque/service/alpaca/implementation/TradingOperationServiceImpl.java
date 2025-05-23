package co.edu.unbosque.traderbosque.service.alpaca.implementation;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.edu.unbosque.traderbosque.exception.InsufficientFundsException;
import co.edu.unbosque.traderbosque.exception.UnauthorizedException;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AccountResponseDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderResponseDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.TransferResponseDTO;
import co.edu.unbosque.traderbosque.model.entity.Order;
import co.edu.unbosque.traderbosque.model.entity.User;
import co.edu.unbosque.traderbosque.model.enums.OrderType;
import co.edu.unbosque.traderbosque.repository.OrderRepository;
import co.edu.unbosque.traderbosque.repository.UserRepository;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IAlpacaFundingService;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IAlpacaOrderService;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.ITradingOperationService;
import jakarta.servlet.http.HttpSession;

@Service
public class TradingOperationServiceImpl implements ITradingOperationService {

    private final IAlpacaOrderService alpacaOrderService;
    private final IAlpacaFundingService alpacaFundingService;
    private final OrderRepository orderRepository;
    private final AlpacaFundingServiceImpl fundingServices;
    private final UserRepository userRepository;

    public TradingOperationServiceImpl(IAlpacaOrderService alpacaOrderService, UserRepository userRepository,
                                     OrderRepository orderRepository, IAlpacaFundingService alpacaFundingService,
                                     AlpacaFundingServiceImpl fundingServices) {
        this.alpacaOrderService = alpacaOrderService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.alpacaFundingService = alpacaFundingService;
        this.fundingServices = fundingServices;
    }

   @Override
    public OrderResponseDTO createOrder(OrderDTO dto, HttpSession session) {
        User user = validarUsuarioSesion(session);
        AccountResponseDTO account = alpacaFundingService.getAccountDetails(user.getAlpacaAccountId());

        if ("buy".equalsIgnoreCase(dto.getSide())) {
            validarFondosSuficientes(account, dto, user);

        } else if ("sell".equalsIgnoreCase(dto.getSide())) {
            validarAccionesSuficientes(dto, user);
        } else {
            throw new IllegalArgumentException("Tipo de operación no reconocido: " + dto.getSide());
        }

        OrderResponseDTO responseDTO = alpacaOrderService.createOrder(user.getAlpacaAccountId(), dto);

        if (responseDTO != null && responseDTO.getId() != null) {
            guardarOrden(responseDTO, user);
        }

        return responseDTO;
    }

    private void validarAccionesSuficientes(OrderDTO dto, User user) {
        // Implementar validación con portafolio del usuario.
        // Por ejemplo, consultar en la base de datos cuántas acciones tiene de dto.getSymbol()
        // y comparar con dto.getQty()

        // Aquí simulo que no tienes ese dato aún:
        // Puedes reemplazar esta parte cuando tengas la tabla/servicio que maneje el portafolio del usuario.
        boolean tieneAccionesSuficientes = true;

        if (!tieneAccionesSuficientes) {
            throw new InsufficientFundsException("No tiene suficientes acciones para vender " +
                    dto.getQty() + " de " + dto.getSymbol());
        }
    }

    private User validarUsuarioSesion(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new UnauthorizedException("Usuario no autenticado");
        }
        return user;
    }

    private double calcularEquity(OrderDTO dto) {
        return switch (dto.getType()) {
            case "market" -> dto.getQty() * dto.getCurrentPrice();
            case "limit", "stop" -> {
                if (dto.getLimitPrice() == null) {
                    throw new IllegalArgumentException("Se requiere un precio límite para órdenes de tipo " + dto.getType());
                }
                yield dto.getQty() * dto.getLimitPrice();
            }
            default -> throw new UnsupportedOperationException("Tipo de orden no soportado: " + dto.getType());
        };
    }

    private void validarFondosSuficientes(AccountResponseDTO account, OrderDTO dto, User user) {
        double balance = Double.parseDouble(account.getLastEquity());

        if (balance == 0.0) {
            List<TransferResponseDTO> transfers = fundingServices.retrieveListOfTransfers(user.getAlpacaAccountId());

            boolean hayTransferenciasPendientes = transfers.stream()
                .anyMatch(transfer ->
                    "PENDING".equalsIgnoreCase(transfer.getStatus()) ||
                    "QUEUED".equalsIgnoreCase(transfer.getStatus())
                );

            if (hayTransferenciasPendientes) {
                throw new InsufficientFundsException("Tiene una recarga en proceso. Por favor, espere a que se complete.");
            }

            boolean tieneTransferenciasLiberadas = transfers.stream()
                .anyMatch(transfer ->
                    "COMPLETE".equalsIgnoreCase(transfer.getStatus()) &&
                    ZonedDateTime.parse(transfer.getHoldUntil()).isBefore(ZonedDateTime.now())
                );

            if (tieneTransferenciasLiberadas) {
                account = alpacaFundingService.getAccountDetails(user.getAlpacaAccountId());
                balance = Double.parseDouble(account.getLastEquity());
            }
        }

        double orderEquity = calcularEquity(dto);

        if (balance < orderEquity) {
            throw new InsufficientFundsException("Fondos insuficientes. Requiere al menos $" + orderEquity +
                    " pero solo tiene $" + balance);
        }
    }

    private void guardarOrden(OrderResponseDTO responseDTO, User user) {
        Order entity = new Order();
        entity.setClientOrderId(responseDTO.getClientOrderId());
        entity.setSymbol(responseDTO.getSymbol());
        entity.setQty(responseDTO.getQty());
        entity.setSide(responseDTO.getSide());

        try {
            entity.setType(OrderType.valueOf(responseDTO.getType().toUpperCase()));
        } catch (IllegalArgumentException e) {
            entity.setType(OrderType.MARKET);
        }

        entity.setStatus(responseDTO.getStatus());
        entity.setFilledQty(responseDTO.getFilledQty());
        entity.setFilledAvgPrice(responseDTO.getFilledAvgPrice());
        entity.setSubmittedAt(responseDTO.getSubmittedAt());
        entity.setFilledAt(responseDTO.getFilledAt());

        entity.setStopPrice(parseDoubleOrDefault(responseDTO.getStopPrice(), 0.0));
        entity.setLimitPrice(parseDoubleOrDefault(responseDTO.getLimitPrice(), 0.0));
        entity.setTimeInForce(responseDTO.getTimeInForce());

        try {
            entity.setRawResponse(new ObjectMapper().writeValueAsString(responseDTO));
        } catch (JsonProcessingException e) {
            entity.setRawResponse("{}");
        }

        entity.setUser(user);
        orderRepository.save(entity);
    }

    private double parseDoubleOrDefault(String input, double defaultValue) {
        try {
            return Double.parseDouble(input);
        } catch (Exception e) {
            return defaultValue;
        }
    }

}