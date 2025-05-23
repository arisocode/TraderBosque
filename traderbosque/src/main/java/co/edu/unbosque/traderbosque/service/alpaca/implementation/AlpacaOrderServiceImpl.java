package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.OrderResponseDTO;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IAlpacaOrderService;

@Service
public class AlpacaOrderServiceImpl implements IAlpacaOrderService {

    private final RestTemplate restTemplate;

    private String apiKey = "CKSB24KB2FW08NFZKOM6";
    private String apiSecret = "jiW0ruUk2V1XSplH3y8uN3TNQ09voefbu784NMr4";

    private final String baseUrl = "https://broker-api.sandbox.alpaca.markets/v1";

    public AlpacaOrderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public OrderResponseDTO createOrder(String alpacaAccountId, OrderDTO dto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(apiKey, apiSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        System.out.println("Enviando orden: " + dto);
        HttpEntity<OrderDTO> request = new HttpEntity<>(dto, headers);

        ResponseEntity<OrderResponseDTO> response = restTemplate.exchange(
                baseUrl + "/trading/accounts/" + alpacaAccountId + "/orders",
                HttpMethod.POST,
                request,
                OrderResponseDTO.class
        );

        return response.getBody();
    }
}
