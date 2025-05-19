package co.edu.unbosque.traderbosque.service.alpaca.implementation;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderResponseDTO;
import co.edu.unbosque.traderbosque.repository.UserRepository;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IAlpacaOrderService;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.ITradingOperationService;
import org.springframework.stereotype.Service;

@Service
public class TradingOperationServiceImpl implements ITradingOperationService {

    private final IAlpacaOrderService alpacaOrderService;
    private final UserRepository userRepository;

    // 🔐 Este ID sería reemplazado por autenticación real en el futuro
    private static final Long USUARIO_PRUEBA_ID = 1L;

    public TradingOperationServiceImpl(IAlpacaOrderService alpacaOrderService, UserRepository userRepository) {
        this.alpacaOrderService = alpacaOrderService;
        this.userRepository = userRepository;
    }

    @Override
    public OrderResponseDTO createOrder(OrderDTO dto) {
        // ID obtenido manualmente desde tu base de datos
        String alpacaAccountId = "e06d45a9-b478-458b-9604-7870b22c8c57";

        return alpacaOrderService.createOrder(alpacaAccountId, dto);
    }
}