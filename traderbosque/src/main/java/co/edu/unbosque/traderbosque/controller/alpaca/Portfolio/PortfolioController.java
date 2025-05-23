package co.edu.unbosque.traderbosque.controller.alpaca.Portfolio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.PortfolioDTO;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IPortfolioService;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("v1/api/portfolio")
public class PortfolioController {

    @Autowired
    private IPortfolioService portfolioService;

    @GetMapping("/")
    public ResponseEntity<PortfolioDTO> getPortfolio(HttpSession session) {
        PortfolioDTO portfolio = portfolioService.getPortfolioForUser(session);
        return ResponseEntity.ok(portfolio);
    }
}