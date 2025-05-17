package co.edu.unbosque.traderbosque.controller.alpaca.Funding;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.*;
import co.edu.unbosque.traderbosque.service.alpaca.interfaces.IAlpacaFundingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/funding")
public class AlpacaFundingController {

    private final IAlpacaFundingService fundingService;

    public AlpacaFundingController(IAlpacaFundingService fundingService) {
        this.fundingService = fundingService;
    }

    // ==== ACH ====
    @PostMapping("/ach/{accountId}")
    public ResponseEntity<AchResponseDTO> createACH(@PathVariable String accountId, @RequestBody AchDTO dto) {
        return ResponseEntity.ok(fundingService.createACHRelationship(accountId, dto));
    }

    @GetMapping("/ach/{accountId}")
    public ResponseEntity<AchResponseDTO> getACH(@PathVariable String accountId) {
        return ResponseEntity.ok(fundingService.retrieveACHRelationship(accountId));
    }

    @DeleteMapping("/ach/{accountId}/{achId}")
    public ResponseEntity<AchResponseDTO> deleteACH(@PathVariable String accountId, @PathVariable String achId) {
        return ResponseEntity.ok(fundingService.deleteACHRelationship(accountId, achId));
    }

    // ==== Bank ====
    @PostMapping("/bank/{accountId}")
    public ResponseEntity<BankResponseDTO> createBank(@PathVariable String accountId, @RequestBody BankDTO dto) {
        return ResponseEntity.ok(fundingService.createBankRelationship(accountId, dto));
    }

    @GetMapping("/bank/{accountId}")
    public ResponseEntity<BankResponseDTO> getBank(@PathVariable String accountId) {
        return ResponseEntity.ok(fundingService.retrieveBankRelationship(accountId));
    }

    @DeleteMapping("/bank/{accountId}/{bankId}")
    public ResponseEntity<BankResponseDTO> deleteBank(@PathVariable String accountId, @PathVariable String bankId) {
        return ResponseEntity.ok(fundingService.deleteBankRelationship(accountId, bankId));
    }

    // ==== Transfer ====
    @PostMapping("/transfer/{accountId}")
    public ResponseEntity<TransferResponseDTO> requestTransfer(@PathVariable String accountId, @RequestBody TransferDTO dto) {
        return ResponseEntity.ok(fundingService.requestNewTransfer(accountId, dto));
    }

    @GetMapping("/transfer/{accountId}")
    public ResponseEntity<List<TransferResponseDTO>> listTransfers(@PathVariable String accountId) {
        return ResponseEntity.ok(fundingService.retrieveListOfTransfers(accountId));
    }

    @DeleteMapping("/transfer/{accountId}/{transferId}")
    public ResponseEntity<TransferResponseDTO> deleteTransfer(@PathVariable String accountId, @PathVariable String transferId) {
        return ResponseEntity.ok(fundingService.closeTransfer(accountId, transferId));
    }
}