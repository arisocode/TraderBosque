package co.edu.unbosque.traderbosque.service.alpaca.interfaces;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.*;

import java.util.List;

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

}
