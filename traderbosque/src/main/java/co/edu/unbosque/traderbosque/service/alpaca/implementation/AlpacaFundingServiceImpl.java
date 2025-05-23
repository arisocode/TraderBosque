package co.edu.unbosque.traderbosque.service.alpaca.implementation;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.AccountResponseDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AchDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AchResponseDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.BankDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.BankResponseDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.TransferDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.TransferResponseDTO;
import co.edu.unbosque.traderbosque.model.entity.AchRelationship;
import co.edu.unbosque.traderbosque.model.entity.User;
import co.edu.unbosque.traderbosque.repository.AchRelationshipRepository;
import co.edu.unbosque.traderbosque.repository.UserRepository;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IAlpacaFundingService;

@Service
public class AlpacaFundingServiceImpl implements IAlpacaFundingService {

    private final RestTemplate restTemplate;
    private final AchRelationshipRepository achRelationshipRepository;
    private final UserRepository userRepository;

    private String apiKey = "CKSB24KB2FW08NFZKOM6";
    private String apiSecret = "jiW0ruUk2V1XSplH3y8uN3TNQ09voefbu784NMr4";

    private final String baseUrl = "https://broker-api.sandbox.alpaca.markets/v1";

    public AlpacaFundingServiceImpl(RestTemplate restTemplate,
                        AchRelationshipRepository achRelationshipRepository,
                        UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.achRelationshipRepository = achRelationshipRepository;
        this.userRepository = userRepository;
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
         AchResponseDTO response = restTemplate.postForObject(
                baseUrl + "/accounts/" + alpacaAccountId + "/ach_relationships",
                request,
                AchResponseDTO.class
        );

        if (response != null && response.getId() != null) {
            
            User user = userRepository.findByAlpacaAccountId(alpacaAccountId)
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con alpacaAccountId: " + alpacaAccountId));

            AchRelationship achEntity = new AchRelationship();
            achEntity.setAccountId(response.getId());
            achEntity.setCreatedAt(OffsetDateTime.parse(response.getCreatedAt()).toLocalDateTime());
            achEntity.setUpdatedAt(OffsetDateTime.parse(response.getUpdatedAt()).toLocalDateTime());
            achEntity.setStatus(response.getStatus());
            achEntity.setAccountOwnerName(response.getAccountOwnerName());
            achEntity.setBankAccountType(response.getBankAccountType());
            achEntity.setBankAccountNumber(response.getBankAccountNumber());
            achEntity.setBankRoutingNumber(response.getBankRoutingNumber());
            achEntity.setNickname(response.getNickname());
            achEntity.setProcessorToken(response.getProcessorToken());
            achEntity.setUserId(user);

            achRelationshipRepository.save(achEntity);
        }

        return response;
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

        ResponseEntity<BankResponseDTO> response = restTemplate.exchange(
                baseUrl + "/accounts/" + alpacaAccountId + "/bank_relationships",
                HttpMethod.POST,
                request,
                BankResponseDTO.class
        );

        return response.getBody();
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

    @Override
    public AccountResponseDTO getAccountDetails(String alpacaAccountId) {
        ResponseEntity<AccountResponseDTO> response = restTemplate.exchange(
            baseUrl + "/accounts/" + alpacaAccountId,
            HttpMethod.GET,
            new HttpEntity<>(buildHeaders()),
            AccountResponseDTO.class
        );
        return response.getBody();
    }
}




