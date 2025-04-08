package co.edu.unbosque.traderbosque.service.alpaca.interfaces;

import co.edu.unbosque.traderbosque.model.DTO.alpaca.AlpacaAccountResponseDTO;

import java.util.List;
import java.util.Optional;

public interface IService <T,K>{

    AlpacaAccountResponseDTO create(T dto) throws Exception;
    Optional<T> read(K id);
    void update(K id, T dto);
    void delete(K id);
    List<T> readAll();

}