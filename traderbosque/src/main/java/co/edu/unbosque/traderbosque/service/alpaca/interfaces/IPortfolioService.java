package co.edu.unbosque.traderbosque.service.alpaca.interfaces;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.PortfolioDTO;
import jakarta.servlet.http.HttpSession;

public interface IPortfolioService {
    PortfolioDTO getPortfolioForUser(HttpSession session);
}
