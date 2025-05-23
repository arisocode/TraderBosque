package co.edu.unbosque.traderbosque.service.alpaca.interfaces;


import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderResponseDTO;
import jakarta.servlet.http.HttpSession;

public interface ITradingOperationService {
    OrderResponseDTO createOrder(OrderDTO dto, HttpSession session);


}
