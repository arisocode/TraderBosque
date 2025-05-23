package co.edu.unbosque.traderbosque.service.alpaca.interfaces;
import java.util.List;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.AccountResponseDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AchDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AchResponseDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.BankDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.BankResponseDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.TransferDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.TransferResponseDTO;

public interface IAlpacaFundingService {

    AchResponseDTO createACHRelationship(String alpacaAccountId, AchDTO dto);
    AchResponseDTO retrieveACHRelationship(String alpacaAccountId);
    AchResponseDTO deleteACHRelationship(String alpacaAccountId, String alpacaACHId);

    BankResponseDTO createBankRelationship(String alpacaAccountId, BankDTO dto);
    BankResponseDTO retrieveBankRelationship(String alpacaAccountId);
    BankResponseDTO deleteBankRelationship(String alpacaAccountId, String alpacaBankId);

    TransferResponseDTO requestNewTransfer(String alpacaAccountId, TransferDTO dto);
    List<TransferResponseDTO> retrieveListOfTransfers(String alpacaAccountId);
    TransferResponseDTO closeTransfer(String alpacaAccountId, String transferId);
    AccountResponseDTO getAccountDetails(String alpacaAccountId);

}
