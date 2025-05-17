package co.edu.unbosque.traderbosque.service.alpaca.interfaces;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderResponseDTO;

public interface IAlpacaOrderService {
    OrderResponseDTO createOrder(String alpacaAccountId, OrderDTO dto);
}
