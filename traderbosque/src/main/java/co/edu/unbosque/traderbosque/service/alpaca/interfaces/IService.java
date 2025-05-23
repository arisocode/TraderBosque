package co.edu.unbosque.traderbosque.service.alpaca.interfaces;

import java.util.List;
import java.util.Optional;

import co.edu.unbosque.traderbosque.model.DTO.ChangePasswordRequestDTO;
import co.edu.unbosque.traderbosque.model.DTO.alpaca.AlpacaAccountResponseDTO;
import co.edu.unbosque.traderbosque.model.entity.User;
import jakarta.servlet.http.HttpSession;

public interface IService <T,K>{

    AlpacaAccountResponseDTO create(T dto) throws Exception;
    Optional<T> read(K id);
    void update(K id, T dto);
    void delete(K id);
    List<T> readAll();
    User readAllUsers(int id);
    int validateCredentials(String username, String password);
    int updatePasswordOnly(ChangePasswordRequestDTO user);
    User readUsername(String username);
    User validarUsuarioSesion(HttpSession session);
}