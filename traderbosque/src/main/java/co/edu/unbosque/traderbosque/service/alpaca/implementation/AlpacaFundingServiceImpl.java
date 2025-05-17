package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.*;
import co.edu.unbosque.traderbosque.model.entity.User;
import co.edu.unbosque.traderbosque.repository.UserRepository;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IAlpacaFundingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class AlpacaFundingServiceImpl implements IAlpacaFundingService {

    private final RestTemplate restTemplate;

    private String apiKey = "CKSB24KB2FW08NFZKOM6";
    private String apiSecret = "jiW0ruUk2V1XSplH3y8uN3TNQ09voefbu784NMr4";

    private final String baseUrl = "https://broker-api.sandbox.alpaca.markets/v1";

    public AlpacaFundingServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private HttpHeaders buildHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(apiKey, apiSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    @Override
    public AchResponseDTO createACHRelationship(String alpacaAccountId, AchDTO dto) {
        HttpEntity<AchDTO> request = new HttpEntity<>(dto, buildHeaders());
        return restTemplate.postForObject(
                baseUrl + "/accounts/" + alpacaAccountId + "/ach_relationships",
                request,
                AchResponseDTO.class
        );
    }


    @Override
    public AchResponseDTO retrieveACHRelationship(String alpacaAccountId) {
        ResponseEntity<AchResponseDTO[]> response = restTemplate.exchange(
                baseUrl + "/accounts/" + alpacaAccountId + "/ach_relationships",
                HttpMethod.GET,
                new HttpEntity<>(buildHeaders()),
                AchResponseDTO[].class
        );
        return response.getBody() != null && response.getBody().length > 0 ? response.getBody()[0] : null;
    }

    @Override
    public AchResponseDTO deleteACHRelationship(String alpacaAccountId, String alpacaACHId) {
        ResponseEntity<AchResponseDTO> response = restTemplate.exchange(
                baseUrl + "/accounts/" + alpacaAccountId + "/ach_relationships/" + alpacaACHId,
                HttpMethod.DELETE,
                new HttpEntity<>(buildHeaders()),
                AchResponseDTO.class
        );
        return response.getBody();
    }


    @Override
    public BankResponseDTO createBankRelationship(String alpacaAccountId, BankDTO dto) {
        HttpEntity<BankDTO> request = new HttpEntity<>(dto, buildHeaders());
        return restTemplate.postForObject(
                baseUrl + "/accounts/" + alpacaAccountId + "/bank_relationships",
                request,
                BankResponseDTO.class
        );
    }

    @Override
    public BankResponseDTO retrieveBankRelationship(String alpacaAccountId) {
        ResponseEntity<BankResponseDTO[]> response = restTemplate.exchange(
                baseUrl + "/accounts/" + alpacaAccountId + "/bank_relationships",
                HttpMethod.GET,
                new HttpEntity<>(buildHeaders()),
                BankResponseDTO[].class
        );
        return response.getBody() != null && response.getBody().length > 0 ? response.getBody()[0] : null;
    }

    @Override
    public BankResponseDTO deleteBankRelationship(String alpacaAccountId, String alpacaBankId) {
        ResponseEntity<BankResponseDTO> response = restTemplate.exchange(
                baseUrl + "/accounts/" + alpacaAccountId + "/bank_relationships/" + alpacaBankId,
                HttpMethod.DELETE,
                new HttpEntity<>(buildHeaders()),
                BankResponseDTO.class
        );
        return response.getBody();
    }

    @Override
    public TransferResponseDTO requestNewTransfer(String alpacaAccountId, TransferDTO dto) {
        HttpEntity<TransferDTO> request = new HttpEntity<>(dto, buildHeaders());
        return restTemplate.postForObject(
                baseUrl + "/accounts/" + alpacaAccountId + "/transfers",
                request,
                TransferResponseDTO.class
        );
    }

    @Override
    public List<TransferResponseDTO> retrieveListOfTransfers(String alpacaAccountId) {
        ResponseEntity<TransferResponseDTO[]> response = restTemplate.exchange(
                baseUrl + "/accounts/" + alpacaAccountId + "/transfers",
                HttpMethod.GET,
                new HttpEntity<>(buildHeaders()),
                TransferResponseDTO[].class
        );
        return Arrays.asList(response.getBody());
    }

    @Override
    public TransferResponseDTO closeTransfer(String alpacaAccountId, String transferId) {
        ResponseEntity<TransferResponseDTO> response = restTemplate.exchange(
                baseUrl + "/accounts/" + alpacaAccountId + "/transfers/" + transferId,
                HttpMethod.DELETE,
                new HttpEntity<>(buildHeaders()),
                TransferResponseDTO.class
        );
        return response.getBody();
    }
}




